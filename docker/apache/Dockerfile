FROM httpd:2.4-alpine

COPY ./httpd-vhosts.conf /usr/local/apache2/conf/extra/httpd-vhosts.conf

RUN sed -i \
		-e 's/^#\(Include .*httpd-vhosts.conf\)/\1/' \
		-e 's/^#\(LoadModule .*mod_ssl.so\)/\1/' \
		-e 's/^#\(LoadModule .*mod_proxy.so\)/\1/' \
		-e 's/^#\(LoadModule .*mod_proxy_balancer.so\)/\1/' \
		-e 's/^#\(LoadModule .*mod_vhost_alias.so\)/\1/' \
		-e 's/^#\(LoadModule .*mod_slotmem_shm.so\)/\1/' \
		-e 's/^#\(LoadModule .*mod_proxy_ajp.so\)/\1/' \
		-e 's/^#\(LoadModule .*mod_proxy_http.so\)/\1/' \
		-e '/Listen 80/a Listen 443' \
		conf/httpd.conf && \
		apk add --no-cache openssl && \
		openssl genrsa -des3 -passout pass:xxxx -out /usr/local/apache2/conf/server.pass.key 2048 && \
		openssl rsa -passin pass:xxxx -in /usr/local/apache2/conf/server.pass.key -out /usr/local/apache2/conf/server.key && \
		rm /usr/local/apache2/conf/server.pass.key && \
		openssl req -new -key /usr/local/apache2/conf/server.key -out /usr/local/apache2/conf/server.csr \
		-subj "/CN=localhost" && \
		openssl x509 -req -days 1024 -in /usr/local/apache2/conf/server.csr -signkey /usr/local/apache2/conf/server.key -out /usr/local/apache2/conf/server.crt
