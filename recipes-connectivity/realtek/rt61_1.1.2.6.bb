DESCRIPTION = "Driver for Ralink RT2501PCI/mPCI/CB (RT61:RT2561/RT2561S/RT2661)"
HOMEPAGE = "http://www.realtek.com.tw"
SECTION = "kernel/modules"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://README;md5=d517aa8db7935eee411104c5be1b2ed9"

inherit module

MACHINE_KERNEL_PR_append = ".0"

SRC_URI = "http://archiv.mixos-support.com/2010_0825_RT61_Linux_STA_v${PV}.tar.bz2 \
	          file://makefile_rt61_1.1.2.6.patch \
"


SRC_URI[md5sum] = "433192a7f42557a33c8255c86141882d"
SRC_URI[sha256sum] = "303b0d96ec2fcb03b874b32bedc248359c639ae1bb1cfccf52d5bbb72ca467d5"

FILES_${PN} += " /lib/firmware/"

S = "${WORKDIR}/2010_0825_RT61_Linux_STA_v${PV}/Module"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR}"

do_install() {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/drivers/net/wireless
	install -m 0644 ${S}/rt61.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/drivers/net/wireless
	install -d ${D}/lib/firmware
	install -m 0644 ${S}/*.bin ${D}/lib/firmware/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"