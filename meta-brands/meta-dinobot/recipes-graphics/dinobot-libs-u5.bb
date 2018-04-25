SRCDATE = "20180425"

inherit pkgconfig

do_install_append() {
	install -d ${D}${libdir}/pkgconfig
	cp -r ${WORKDIR}/pkgconfig/*.pc ${D}${libdir}/pkgconfig/
}

require dinobot-libs.inc

SRC_URI[md5sum] = "898871b5634a3ba1e1e3640f1f808d04"
SRC_URI[sha256sum] = "2a6f74caf2c8ecf6366b293de8028a3b245c807948f3bf2f220593554f19fe13"
