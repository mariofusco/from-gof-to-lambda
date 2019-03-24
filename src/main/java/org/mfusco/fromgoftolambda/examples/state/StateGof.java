package org.mfusco.fromgoftolambda.examples.state;

interface Statelike {
    void writeName(StateContext context, String name);  
}

class StateLowerCase implements Statelike {

    @Override
    public void writeName(final StateContext context, final String name) {
        System.out.println(name.toLowerCase());
        context.setState(new StateMultipleUpperCase());
    }

}

class StateMultipleUpperCase implements Statelike {
    /** Counter local to this state */
    private int count = 0;

    @Override
    public void writeName(final StateContext context, final String name) {
        System.out.println(name.toUpperCase());
        /* Change state after StateMultipleUpperCase's writeName() gets invoked twice */
        if(++count > 1) {
            context.setState(new StateLowerCase());
        }
    }
    
}

class StateContext {
    private Statelike myState;
    StateContext() {
        setState(new StateLowerCase());
    }

    /**
     * Setter method for the state.
     * Normally only called by classes implementing the State interface.
     * @param newState the new state of this context
     */
    void setState(final Statelike newState) {
        myState = newState;
    }

    public void writeName(final String name) {
        myState.writeName(this, name);
    }
}


public class StateGof {

    public static void main(String[] args) {
        final StateContext sc = new StateContext();

        sc.writeName("january");
        sc.writeName("february");
        sc.writeName("march");
        sc.writeName("april");
        sc.writeName("may");
        sc.writeName("june");
        sc.writeName("july");
        sc.writeName("august");
        sc.writeName("september");
        sc.writeName("october");
        sc.writeName("november");
        sc.writeName("december");

    }
}



