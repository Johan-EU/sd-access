COMPILE_DEPS = CORE_DEPS + KRYO + JACKSON + [
    "//core/store/serializers:onos-core-serializers",
    "//drivers/p4runtime:onos-drivers-p4runtime",
    "//pipelines/basic:onos-pipelines-basic",
    "//protocols/p4runtime/api:onos-protocols-p4runtime-api",
    "//protocols/p4runtime/model:onos-protocols-p4runtime-model",
    "@io_grpc_grpc_java//core",
    "@io_grpc_grpc_java//netty",
    "@minimal_json//jar",
    # "//protocols/bmv2/thrift-api:onos-protocols-bmv2-thrift-api",
    #"//lib:libthrift",
]

BUNDLES = [
    ":onos-drivers-bmv2",
    # "//lib:libthrift",
    # "//protocols/bmv2/thrift-api:onos-protocols-bmv2-thrift-api",
]

osgi_jar(
    deps = COMPILE_DEPS,
    # FIXME: enable building other classes as soon as we implement BUILD for
    # protocols/bmv2 (based on Thrift)
    srcs = [
        "src/main/java/org/onosproject/drivers/bmv2/Bmv2DriversLoader.java",
        "src/main/java/org/onosproject/drivers/bmv2/Bmv2PipelineProgrammable.java",
    ],
    resources_root = "src/main/resources",
    resources = glob(["src/main/resources/**"]),
)

onos_app (
    app_name = "org.onosproject.drivers.bmv2",
    title = "BMv2 Drivers",
    category = "Drivers",
    url = "http://onosproject.org",
    description = "Adds support for BMv2 devices.",
    included_bundles = BUNDLES,
    required_apps = [
        "org.onosproject.drivers.p4runtime",
        "org.onosproject.drivers.gnmi",
        "org.onosproject.pipelines.basic",
    ],
)
