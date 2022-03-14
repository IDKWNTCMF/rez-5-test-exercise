#!/bin/bash

objdump -S grep | grep -i 'callq  4ad0 <malloc@plt>' | wc -l
