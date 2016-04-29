package org.mfusco.fromgoftolambda.examples.visitor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class VisitorLambda
{

  public static class LambdaVisitor<A> implements Function<Object, Optional<A>>
  {
    Map<Class<?>, NonTotalFunction<?, A>> fMap = new HashMap<>();

    public <B> LambdaVisitor<A> with(NonTotalFunction<B, A> f)
    {
      fMap.put(f.clazz, f);
      return this;
    }

    @Override
    public Optional<A> apply(Object o)
    {
      return fMap.getOrDefault(o.getClass(), NonTotalFunction.empty()).apply(o);
    }
  }

  public static class NonTotalFunction<T, R> implements Function<Object, Optional<R>>
  {
    public final Class<T> clazz;
    private final Function<T, R> fun;

    public NonTotalFunction(Class<T> clazz, Function<T, R> fun)
    {
      this.clazz = clazz;
      this.fun = fun;
    }

    @SuppressWarnings("rawtypes")
    private static final NonTotalFunction EMPTY = new Empty();

    @SuppressWarnings("unchecked")
    @Override
    public Optional<R> apply(Object o)
    {
      if (clazz.isAssignableFrom(o.getClass()))
        return Optional.of(fun.apply((T) o));
      else
        return Optional.empty();
    }

    public static <A, B> NonTotalFunction<A, B> of(Class<A> cl, Function<A, B> fu)
    {
      return new NonTotalFunction<>(cl, fu);
    }

    @SuppressWarnings("unchecked")
    public static <A, B> NonTotalFunction<A, B> empty()
    {
      return EMPTY;
    }

    static class Empty extends NonTotalFunction<Void, Void>
    {
      public Empty()
      {
        super(Void.TYPE, (Void x) -> null);
      }
    }
  }

  public static class Square
  {
    final double side;

    public Square(double side)
    {
      this.side = side;
    }
  }

  public static class Circle
  {
    final double radius;

    public Circle(double radius)
    {
      this.radius = radius;
    }
  }

  public static class Rectangle
  {
    final double width;
    final double height;

    public Rectangle(double width, double height)
    {
      this.width = width;
      this.height = height;
    }
  }

  static Function<Object, Optional<Double>> areaVisitor = new LambdaVisitor<Double>()
      .with(NonTotalFunction.of(Square.class, s -> s.side * s.side))
      .with(NonTotalFunction.of(Circle.class, c -> Math.PI * c.radius * c.radius))
      .with(NonTotalFunction.of(Rectangle.class, r -> r.height * r.width));

  static Function<Object, Optional<Double>> perimeterVisitor = new LambdaVisitor<Double>()
      .with(NonTotalFunction.of(Square.class, s -> 4 * s.side))
      .with(NonTotalFunction.of(Circle.class, c -> 2 * Math.PI * c.radius));

  public static void main(String[] args)
  {
    List<Object> figures = Arrays.asList(new Circle(4), new Square(5), new Rectangle(6, 7));
    figures.forEach(o -> System.out.println(areaVisitor.apply(o)));
    figures.forEach(o -> System.out.println(perimeterVisitor.apply(o)));
  }
}