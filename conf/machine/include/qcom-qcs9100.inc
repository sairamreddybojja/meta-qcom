# Configurations and variables for QCS9100 SoC family.

SOC_FAMILY = "qcs9100"
require conf/machine/include/qcom-base.inc
require conf/machine/include/qcom-common.inc

DEFAULTTUNE = "armv8-2a-crypto"
require conf/machine/include/arm/arch-armv8-2a.inc

KERNEL_CMDLINE_EXTRA ?= "earlycon"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " \
    packagegroup-qcom-boot-essential \
    packagegroup-qcom-audio \
"

MACHINE_EXTRA_RRECOMMENDS += " \
    packagegroup-qcom-boot-additional \
"

EXTRA_IMAGEDEPENDS += "firmware-qcom-boot-qcs9100"
