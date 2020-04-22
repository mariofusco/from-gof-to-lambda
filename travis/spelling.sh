#!/bin/bash

# takes args: check or fix, otherwise

if [ $1 = "check" ]; then
  ARGS=""
fi

echo $ARGS

curl -L -o ./install-misspell.sh https://git.io/misspell &> /dev/null
sh ./install-misspell.sh &> /dev/null
rm ./install-misspell.sh
mv ./bin/misspell /usr/local/bin
git ls-files -m | xargs misspell
