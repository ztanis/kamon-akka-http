/*
 * =========================================================================================
 * Copyright © 2013-2018 the kamon project <http://kamon.io/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 * =========================================================================================
 */

package kamon.akka.http.instrumentation.kanela.advisor;

import akka.NotUsed;
import akka.http.scaladsl.model.HttpRequest;
import akka.http.scaladsl.model.HttpResponse;
import akka.stream.scaladsl.Flow;
import kamon.akka.http.instrumentation.ServerFlowWrapper;
import kanela.agent.libs.net.bytebuddy.asm.Advice;

/**
 * Advisor for akka.http.scaladsl.HttpExt::bindAndHandle
 */
public class ServerFlowAdvisor {
    @Advice.OnMethodEnter
    public static void onEnter(@Advice.Argument(value = 0, readOnly = false) Flow<HttpRequest, HttpResponse, NotUsed> handler,
                               @Advice.Argument(1) String iface,
                               @Advice.Argument(2) Integer port) {
        handler = ServerFlowWrapper.apply(handler, iface, port);
    }
}