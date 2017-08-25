/*
 * Copyright 2017-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.incubator.protobuf.models.core;

import com.google.common.collect.Sets;
import org.onosproject.core.Application;
import org.onosproject.core.ApplicationRole;
import org.onosproject.core.DefaultApplication;
import org.onosproject.grpc.core.models.ApplicationProtoOuterClass.ApplicationProto;
import org.onosproject.incubator.protobuf.models.security.PermissionProtoTranslator;
import org.onosproject.security.Permission;

import java.util.Optional;
import java.util.Set;

import static org.onosproject.grpc.core.models.ApplicationProtoOuterClass.ApplicationProto.getDefaultInstance;

/**
 * gRPC ApplicationProto message to equivalent ONOS Application conversion related utilities.
 */
public final class ApplicationProtoTranslator {

    /**
     * Translates gRPC Application to {@link Application}.
     *
     * @param app gRPC message
     * @return {@link Application}
     */
    public static Application translate(ApplicationProto app) {

        Set<Permission> permissions = Sets.newHashSet();

        app.getPermissionsList().forEach(p ->
                permissions.add(PermissionProtoTranslator.translate(p)));

        return new DefaultApplication(ApplicationIdProtoTranslator.translate(app.getAppId()),
                VersionProtoTranslator.translate(app.getVersion()), app.getTitle(),
                app.getDescription(), app.getOrigin(), app.getCategory(), app.getUrl(),
                app.getReadme(), app.toByteArray(),
                (ApplicationRole) ApplicationEnumsProtoTranslator.translate(app.getRole()).get(),
                permissions, Optional.empty(), app.getFeaturesList(), app.getRequiredAppsList());
    }

    /**
     * Translates {@link Application} to gRPC Application message.
     *
     * @param application {@link Application}
     * @return gRPC message
     */
    public static ApplicationProto translate(Application application) {

        if (application != null) {
            return ApplicationProto.newBuilder()
                    .setAppId(ApplicationIdProtoTranslator.translate(application.id()))
                    .setCategory(application.category())
                    .setDescription(application.description())
                    .setOrigin(application.origin())
                    .setReadme(application.readme())
                    .setTitle(application.title())
                    .setUrl(application.url())
                    .setVersion(VersionProtoTranslator.translate(application.version()))
                    .setRole(ApplicationEnumsProtoTranslator.translate(application.role()))
                    .build();
        }

        return getDefaultInstance();
    }

    // Utility class not intended for instantiation.
    private ApplicationProtoTranslator() {}
}
