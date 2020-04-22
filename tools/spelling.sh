#!/bin/bash


echo "hello you are starting the spell check"
sh ./tools/install-misspell.sh &> /dev/null
#mv ./bin/misspell /usr/local/bin
#chmod +x ./bin/misspell
git ls-files  | xargs "misspell"
