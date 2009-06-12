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

package org.mifos.application.collectionsheet.util.helpers;

import org.mifos.application.accounts.savings.business.SavingsBO;
import org.mifos.application.util.helpers.YesNoFlag;

public class BulkEntrySavingsCache {

    private SavingsBO account;

    private YesNoFlag yesNoFlag;

    public BulkEntrySavingsCache(SavingsBO account, YesNoFlag yesNoFlag) {
        this.account = account;
        this.yesNoFlag = yesNoFlag;
    }

    public SavingsBO getAccount() {
        return account;
    }

    public void setAccount(SavingsBO account) {
        this.account = account;
    }

    public YesNoFlag getYesNoFlag() {
        return yesNoFlag;
    }

    public void setYesNoFlag(YesNoFlag yesNoFlag) {
        this.yesNoFlag = yesNoFlag;
    }

}
