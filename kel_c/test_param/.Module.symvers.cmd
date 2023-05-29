cmd_/opt/kel_c/test_param/Module.symvers := sed 's/ko$$/o/' /opt/kel_c/test_param/modules.order | scripts/mod/modpost  -a   -o /opt/kel_c/test_param/Module.symvers -e -i Module.symvers   -T -
