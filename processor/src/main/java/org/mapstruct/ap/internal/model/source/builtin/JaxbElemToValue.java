/**
 *  Copyright 2012-2015 Gunnar Morling (http://www.gunnarmorling.de/)
 *  and/or other contributors as indicated by the @authors tag. See the
 *  copyright.txt file in the distribution for a full listing of all
 *  contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mapstruct.ap.internal.model.source.builtin;

import java.util.Set;

import javax.xml.bind.JAXBElement;

import org.mapstruct.ap.internal.model.common.Parameter;
import org.mapstruct.ap.internal.model.common.Type;
import org.mapstruct.ap.internal.model.common.TypeFactory;

import static org.mapstruct.ap.internal.util.Collections.asSet;

/**
 * @author Sjaak Derksen
 */
public class JaxbElemToValue extends BuiltInMethod {

    private final Parameter parameter;
    private final Type returnType;
    private final Set<Type> importTypes;

    public JaxbElemToValue(TypeFactory typeFactory) {
        this.parameter = new Parameter( "element", typeFactory.getType( JAXBElement.class ) );
        this.returnType = typeFactory.getType( Object.class );
        this.importTypes = asSet( typeFactory.getType( JAXBElement.class ) );
    }

    @Override
    public boolean doTypeVarsMatch(Type sourceType, Type targetType) {
        boolean match = false;
        if ( sourceType.getTypeParameters().size() == 1 ) {
            match = sourceType.getTypeParameters().get( 0 ).isAssignableTo( targetType );
        }
        return match;
    }

    @Override
    public Parameter getParameter() {
        return parameter;
    }

    @Override
    public Type getReturnType() {
        return returnType;
    }

    @Override
    public Set<Type> getImportTypes() {
        return importTypes;
    }
}