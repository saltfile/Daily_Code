cmd_/opt/kel_c/test_param/modules.order := {   echo /opt/kel_c/test_param/test.ko; :; } | awk '!x[$$0]++' - > /opt/kel_c/test_param/modules.order
