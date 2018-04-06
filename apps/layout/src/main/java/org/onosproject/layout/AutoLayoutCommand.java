/*
 * Copyright 2018-present Open Networking Foundation
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
package org.onosproject.layout;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.onosproject.cli.AbstractShellCommand;

/**
 * Lists all the default lease parameters offered by the DHCP Server.
 */
@Command(scope = "onos", name = "topo-layout",
        description = "Lays out the elements in the topology using the specified algorithm")
public class AutoLayoutCommand extends AbstractShellCommand {

    @Argument(index = 0, name = "algorithm",
            description = "Layout algorithm to use for the layout")
    String algorithm = "access";

    @Override
    protected void execute() {
        RoleBasedLayoutManager mgr = get(RoleBasedLayoutManager.class);
        switch (algorithm) {
            case "spine-leaf":
                mgr.layout(new AccessNetworkLayout());
                break;
            case "access":
                mgr.layout(new AccessNetworkLayout());
                break;
            case "hag-access":
                mgr.layout(new AccessNetworkLayout());
                break;
            default:
                print("Unsupported layout algorithm %s", algorithm);
        }
    }
}
