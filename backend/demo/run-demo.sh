#!/usr/bin/env bash
# Demo script to run a focused test in the backend (bash)
set -euo pipefail
ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
echo "Running StudentControllerTest (focused demo)..."
cd "$ROOT_DIR/backend"
mvn -DtrimStackTrace=false -Dtest=StudentControllerTest test
echo
echo "Focused test finished. To run all tests: mvn test"
