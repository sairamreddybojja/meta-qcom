inherit autotools pkgconfig systemd

DESCRIPTION = "pal"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM += "file://Pal.cpp;beginline=31;endline=32;md5=e733afaf233fbcbc22769d0a9bda0b3e \
                     file://inc/PalDefs.h;beginline=30;endline=31;md5=e733afaf233fbcbc22769d0a9bda0b3e"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/arpal-lx.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.0.0"
SRCREV     = "b7201e5244db4ab807b9b3e8f5ec46fa7cfabdf8"

SRC_URI  = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/arpal-lx \
            file://adsprpcd_audiopd.service"

S = "${UNPACKDIR}/audio/opensource/arpal-lx"

DEPENDS = "tinyalsa tinycompress qcom-agm qcom-kvh2xml qcom-audioroute fastrpc qcom-pal-headers"

EXTRA_OECONF += " --with-glib --with-syslog"

SYSTEMD_SERVICE:${PN} += "adsprpcd_audiopd.service"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"

do_install:append () {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/adsprpcd_audiopd.service ${D}${systemd_system_unitdir}
    install -d ${D}${systemd_system_unitdir}/multi-user.target.wants/
    ln -sf ${systemd_system_unitdir}/adsprpcd_audiopd.service \
           ${D}${systemd_system_unitdir}/multi-user.target.wants/adsprpcd_audiopd.service
}

FILES:${PN} += "${libdir}/*.so ${libdir}/pkgconfig/ ${systemd_unitdir}/system/* ${sysconfdir}/* ${bindir}/*"
FILES:${PN}-dev = "${libdir}/*.la ${includedir}"
