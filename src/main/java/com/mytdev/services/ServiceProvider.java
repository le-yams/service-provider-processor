/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 * 
 * Portions Copyrighted 2013 Yann D'Isanto
 */
package com.mytdev.services;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declarative registration of a singleton service provider. By marking an
 * implementation class with this annotation, you automatically register that
 * implementation that will be available through the ServiceLoader. The class
 * must be public and have a public no-argument constructor.
 * <p>Example of usage:
 * <pre>
 * package my.stuff.impl;
 * import my.stuff.api.MyInterface;
 * import com.mytdev.services.ServiceProvider;
 * &#64;ServiceProvider(service=MyInterface.class)
 * public class MyInterfaceImpl implements MyInterface {...}
 * </pre>
 * <p>would result in a resource file
 * <code>META-INF/services/my.stuff.api.MyInterface</code> containing the
 * single line of text:
 * <code>my.stuff.impl.MyInterfaceImpl</code>
 *
 * @see java.util.ServiceLoader
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface ServiceProvider {

    /**
     * The interface (or abstract class) to register this implementation under.
     * It is an error if the implementation class is not in fact assignable to
     * the interface.
     * <p>If you need to register one class under multiple interfaces, use
     * {@link ServiceProviders}.
     */
    Class<?> service();

    /**
     * An optional position in which to register this service relative to
     * others. Lower-numbered services are returned in the lookup result first.
     * Services with no specified position are returned last.
     */
    int position() default Integer.MAX_VALUE;
}