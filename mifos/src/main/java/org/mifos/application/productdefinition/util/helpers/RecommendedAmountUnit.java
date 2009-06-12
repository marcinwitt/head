/*
 * Copyright (c) 2005-2009 Grameen Foundation USA
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 */

package org.mifos.application.productdefinition.util.helpers;

public enum RecommendedAmountUnit {
    PER_INDIVIDUAL((short) 1), COMPLETE_GROUP((short) 2);

    private Short value;

    private RecommendedAmountUnit(Short value) {
        this.value = value;
    }

    public Short getValue() {
        return value;
    }

    public static RecommendedAmountUnit fromInt(int value) {
        for (RecommendedAmountUnit candidate : RecommendedAmountUnit.values()) {
            if (candidate.getValue() == value) {
                return candidate;
            }
        }
        throw new RuntimeException("no recommended amount unit " + value);
    }

}
