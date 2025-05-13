FROM ubuntu:latest
LABEL authors="gabrielgodoi"

ENTRYPOINT ["top", "-b"]