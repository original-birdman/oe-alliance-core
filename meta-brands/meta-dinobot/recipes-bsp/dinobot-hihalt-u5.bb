SUMMARY = "halt for dinobot Model ${MACHINE}"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCDATE = "20180425"

PV = "${SRCDATE}"
PR = "r0"

SRC_URI  = "http://source.mynonpublic.com/dinobot/${MACHINE}-hihalt-${SRCDATE}.tar.gz"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/hihalt ${D}/${bindir}
    install -m 0755 ${S}/hipmoc ${D}/${bindir}
    install -m 0755 ${S}/timerTask.py ${D}/${bindir}
}

do_package_qa() {
}

FILES_${PN}  = "${bindir}/hihalt"
FILES_${PN} += "${bindir}/hipmoc"
FILES_${PN} += "${bindir}/timerTask.py"

SRC_URI[md5sum] = "bb5d180869d147b3cdbbb6a5ac0a96f4"
SRC_URI[sha256sum] = "7a8c95df840396177e6f8095e40a4efc29ac9f61b4ba9863a6f5cfa94b9223dc"


