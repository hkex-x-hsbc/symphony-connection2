#!/bin/sh

ps -ef |grep symphony-connection2 |grep -v grep |awk '{print $2}'|xargs kill -9
