#!/bin/bash
set -euo pipefail

echo "$(secrets.GOOGLE_JSON_KEY)" > google-services.json
mkdir -p app/src/release
cp google-services.json app/src/release/google-services.json