FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR .= ".3"

S = "${WORKDIR}"

PACKAGE_ARCH := "${MACHINE_ARCH}"

SRC_URI += " \
    file://67_init_hddown.dpatch \
    file://92_sata-hddown.dpatch \
    ${@base_contains("MACHINE_FEATURES", "gbprogress", "file://proc_progressgb.patch", "file://proc_progress.patch", d)} \
    ${@base_contains("MACHINE_FEATURES", "gbplusprogress", "file://proc_progressgbplus.patch", "", d)} \
    ${@base_contains("MACHINE_FEATURES", "vuprogress", "file://proc_progress_vuplus.patch", "", d)} \
    ${@base_contains("MACHINE", "inihdp", "file://proc_progress_ini.patch", "", d)}"

do_install_append() {
    rm ${D}${sysconfdir}/rc*.d/*bootlogd
}

do_install_append_spark7162() {
    # AOTOM rtc needs to be in localtime or standby time display will be wrong.
    sed -i -e '/^UTC=yes/{
s/^/# /;
a# *** aotom RTC on SPARK needs hwclock in localtime ***
aUTC=no
}' ${D}${sysconfdir}/default/rcS
}
