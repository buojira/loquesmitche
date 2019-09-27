#
# Copyright 2019, TOTVS S.A.
# All rights reserved.
#

SHELL = /bin/bash

VERSION ?= $(shell git rev-parse --short=7 HEAD)
IMAGE ?= platform/loquesmitche
ROOTPATH ?= $(shell git rev-parse --show-toplevel)
TMP_DIR := "$(shell mktemp -d $(ROOTPATH)/tmp.build.XXXXXXXXXX)"
ARTIFACTS ?= "$(shell git rev-parse --show-toplevel)/target/loquesmitche.jar"
DOCKERPATH ?= "$(shell git rev-parse --show-toplevel)/docker"

# Define here the list of images that should be builded
LIST_OF_IMAGES_TO_BUILD := $(IMAGE)
export

# ensure that latest isn't use for build
define isLatest
	@[ $(VERSION) != "latest" ] || (echo "VERSION can't be latest in $(@)"; exit 1)
endef

# Rule "build"
.PHONY: build
build: ## Build the Dockerfile
	$(call isLatest)
	cp -R $(ARTIFACTS) $(TMP_DIR)
	cp -R $(DOCKERPATH)/* $(TMP_DIR)
	cd $(TMP_DIR)
	docker build --force-rm \
		--no-cache -t $(IMAGE):$(VERSION) \
		$(TMP_DIR)
	sleep 0.2
	rm -rf $(TMP_DIR)

# Rule "build:local"
.PHONY: build\:local
build\:local: ## Build the Dockerfile.local
	$(call isLatest)
	cp -R $(ARTIFACTS) $(TMP_DIR)
	cp -R $(DOCKERPATH)/* $(TMP_DIR)
	cd $(TMP_DIR)
	docker build --force-rm \
		-t "$(IMAGE)":local \
		$(TMP_DIR)
	sleep 0.2
	rm -rf $(TMP_DIR)

# Rule "build:perfmon"
.PHONY: build\:perfmon
build\:perfmon:
	$(call isLatest)
	cp -R $(ARTIFACTS) $(TMP_DIR)
	cp -R $(DOCKERPATH)/* $(TMP_DIR)
	cd $(TMP_DIR)
	docker build --force-rm \
		-t "$(IMAGE)":perfmon \
		--build-arg perfmon=true \
		$(TMP_DIR)
	sleep 0.2
	rm -rf $(TMP_DIR)

# Rule "push"
.SILENT: push
push:
	# produce a failure return code if any command return error \
	set -eo pipefail; \
	make push -f MakeUtils; \

# Rule "info"
.PHONY: info
.SILENT: info
info:
	# produce a failure return code if any command return error \
	set -eo pipefail; \
	make -s info -f MakeUtils;

# Rule "run-db"
.PHONY: run-db
run-db:
	docker-compose -f docker-db.yml up

# Rule "run-app"
.PHONY: run-app
run-app:
	docker-compose -f docker-compose-local.yml up
