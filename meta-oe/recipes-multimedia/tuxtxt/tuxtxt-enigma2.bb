SUMMARY = "tuxbox tuxtxt for 32bit framebuffer"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"
DEPENDS = "freetype libtuxtxt"
SUMMARY = "tuxbox tuxtxt for enigma2"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit gitpkgv

SRC_URI = "git://github.com/OpenPLi/tuxtxt.git;protocol=git \
           file://0001-Workaround-for-Gigablue-Quad-receivers.patch \
           file://0002-Use-separate-transparency-for-menu-and-teletext.patch \
           file://acinclude_fix_DVB_API_VERSION_check_for_gcc5.patch \
           file://tuxtxt.ttf \
           file://tuxtxt_nonbold.ttf \
"

SRC_URI_append_wetekplay = " file://0001-add-HBGIC-for-wetek.patch"
SRC_URI_append_wetekplay2 = " file://0001-add-HBGIC-for-wetek.patch"
SRC_URI_append_odroidc2 = " file://0001-add-HBGIC-for-wetek.patch"

SRC_URI_append_openatv = " \
    file://tuxtxt_FHD.patch \
    "

S = "${WORKDIR}/git/tuxtxt"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
PR = "r17"

do_configure_prepend() {
    touch ${S}/NEWS
    touch ${S}/README
    touch ${S}/AUTHORS
    touch ${S}/ChangeLog
}

do_configure_prepend_openatv () {
    sed 's/UseTTF 0/UseTTF 1/g' -i ${S}/data/tuxtxt2.conf
    sed 's/TTFWidthFactor16 28/TTFWidthFactor16 29/g' -i ${S}/data/tuxtxt2.conf
    sed 's/TTFHeightFactor16 16/TTFHeightFactor16 14/g' -i ${S}/data/tuxtxt2.conf
    sed 's/TTFShiftY 0/TTFShiftY 2/g' -i ${S}/data/tuxtxt2.conf
    touch ${S}/NEWS
    touch ${S}/README
    touch ${S}/AUTHORS
    touch ${S}/ChangeLog
}

do_configure_prepend_openvix () {
    sed 's/UseTTF 0/UseTTF 1/g' -i ${S}/data/tuxtxt2.conf
    sed 's/TTFWidthFactor16 28/TTFWidthFactor16 26/g' -i ${S}/data/tuxtxt2.conf
    sed 's/TTFHeightFactor16 16/TTFHeightFactor16 14/g' -i ${S}/data/tuxtxt2.conf
    sed 's/TTFShiftY 0/TTFShiftY 2/g' -i ${S}/data/tuxtxt2.conf
    touch ${S}/NEWS
    touch ${S}/README
    touch ${S}/AUTHORS
    touch ${S}/ChangeLog
}

do_install_append_openatv () {
    install -d ${D}/usr/share/fonts
    rm -f ${D}/usr/share/fonts/tuxtxt.ttf
    rm -f ${D}/usr/share/fonts/tuxtxt_nonbold.ttf
    cp -f ${WORKDIR}/tuxtxt.ttf ${D}/usr/share/fonts/tuxtxt.ttf
    cp -f ${WORKDIR}/tuxtxt_nonbold.ttf ${D}/usr/share/fonts/tuxtxt_nonbold.ttf
}

PACKAGES = "${PN}-src ${PN}-dbg ${PN}-dev ${PN}"
FILES_${PN}-src = "/usr/src /usr/lib/enigma2/python/Plugins/Extensions/Tuxtxt/*.py"
FILES_${PN} = "/usr/lib/libtuxtxt32bpp.so.* /usr/share/fonts /usr/lib/enigma2/python/Plugins/Extensions/Tuxtxt/*.pyo /etc/tuxtxt"
CONFFILES_${PN} = "/etc/tuxtxt/tuxtxt2.conf"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-boxtype=generic --with-configdir=/etc \
    ${@bb.utils.contains("MACHINE_FEATURES", "textlcd", "--with-textlcd" , "", d)} \
     "
