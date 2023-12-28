# Build stage
ARG BUILDER_IMAGE
ARG RUNTIME_IMAGE
ARG PROJECT_ROOT

FROM ${RUNTIME_IMAGE}

WORKDIR ${PROJECT_ROOT}

COPY game.jar ${PROJECT_ROOT}/

CMD ["java", "-jar", "/usr/lib/game/game.jar"]
