SUMMARY = "MixOS version info"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "MixOS Support"

require conf/license/license-gplv2.inc

PV = "${IMAGE_VERSION}"
PR = "r0-${DATETIME}-${DISTRO_TYPE}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

URL = "http://www.mixos-support.com"

S = "${WORKDIR}"

inherit autotools

PACKAGES = "${PN}"

do_install() {
    if [ "${DISTRO_TYPE}" = "release" ] ; then
        BUILDTYPE="1"
    else
        BUILDTYPE="0"
    fi
    if [ "${BASE_FEED}" = "beta" ] ; then
        BUILDTYPE="1"
    fi
    install -d ${D}/etc
    # generate /etc/image-version
    echo "box_type=${MACHINEBUILD}" > ${D}/etc/image-version
    echo "build_type=${BUILDTYPE}" >> ${D}/etc/image-version
    echo "version=${IMAGE_VERSION}" >> ${D}/etc/image-version
    echo "build=${BUILD_VERSION}" >> ${D}/etc/image-version
    echo "date=${DATETIME}" >> ${D}/etc/image-version
    echo "comment=MixOS" >> ${D}/etc/image-version
    echo "target=9" >> ${D}/etc/image-version
    echo "creator=MixOS" >> ${D}/etc/image-version
    echo "url=${URL}" >> ${D}/etc/image-version
    echo "catalog=${URL}" >> ${D}/etc/image-version
    echo "${MACHINE}" > ${D}/etc/model
}

FILES_${PN} += "/etc"

