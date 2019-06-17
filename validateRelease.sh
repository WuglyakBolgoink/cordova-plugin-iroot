#!/bin/bash

set -e

# explicit declaration that this script needs a $TAG variable passed in e.g TAG=1.2.3 ./script.sh
TAG=$TAG
TAG_SYNTAX='^[0-9]+\.[0-9]+\.[0-9]+(-.+)*$'

# get version found in package.json. This is the source of truth
PACKAGE_VERSION=$(cat package.json | jq -r '.version')

# validate tag has format x.y.z
if [[ "$(echo $TAG | grep -E $TAG_SYNTAX)" == "" ]]; then
  echo "tag $TAG is invalid. Must be in the format x.y.z or x.y.z-SOME_TEXT"
  exit 1
fi

# validate that TAG == version found in package.json
if [[ $TAG != $PACKAGE_VERSION ]]; then
  echo "tag $TAG is not the same as package version found in package.json $PACKAGE_VERSION"
  exit 1
fi

echo "TAG and PACKAGE_VERSION are valid"
