# Start from the latest Jenkins base image
FROM jenkins/jenkins:jdk17

# Switch to root user to install additional software
USER root

# Install OpenJDK-18
RUN apt-get update
    #apt-get install -y openjdk-8-jre && \
    #update-alternatives --config java

# Install Maven
RUN apt-get install -y maven

# Install Docker client (assuming the Docker daemon is running on the host)
RUN apt-get install -y apt-transport-https ca-certificates curl software-properties-common gnupg2 && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add - && \
    add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable" && \
    apt-get update && \
    apt-get install -y docker-ce-cli

# Add your custom settings.xml for Maven
COPY settings.xml /usr/share/maven/conf/settings.xml

# Copy custom Jenkins plugins, configurations, jobs, etc.
# COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
# RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Switch back to the Jenkins user
USER jenkins

# Set up Jenkins home directory as a volume
VOLUME /var/jenkins_home

# Expose default Jenkins port
EXPOSE 8080
EXPOSE 50000

# Start Jenkins
ENTRYPOINT ["/usr/bin/tini", "--", "/usr/local/bin/jenkins.sh"]
