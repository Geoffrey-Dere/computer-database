FROM jenkins:2.46.2

USER root
RUN apt-get update \
      && apt-get install -y sudo \
      && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers
 

# docker run -d --name=jenkins -v /var/run/docker.sock:/var/run/docker.sock -v $(which docker):/usr/bin/docker -p 8082:8080 -v test:/test -v    prod:/prod xercasy/jenkins
