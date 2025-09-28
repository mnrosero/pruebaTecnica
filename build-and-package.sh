#!/usr/bin/env bash
set -e
mvn -f ms-clientes clean package -DskipTests
mvn -f ms-cuentas clean package -DskipTests
echo "Build finished."
