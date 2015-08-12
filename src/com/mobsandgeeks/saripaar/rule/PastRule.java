/*
 * Copyright (C) 2014 Mobs & Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobsandgeeks.saripaar.rule;

import android.content.Context;

import com.mobsandgeeks.saripaar.ContextualAnnotationRule;
import com.mobsandgeeks.saripaar.ValidationContext;
import com.mobsandgeeks.saripaar.annotation.Past;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ragunath Jawahar {@literal <rj@mobsandgeeks.com>}
 * @since 2.0
 */
public class PastRule extends ContextualAnnotationRule<Past, String> {
    private int mDateFormatResId;
    private String mDateFormatString;

    protected PastRule(final ValidationContext validationContext, final Past past) {
        super(validationContext, past);
        mDateFormatResId = past.dateFormatResId();
        mDateFormatString = past.dateFormat();
    }

    @Override
    public boolean isValid(final String dateString) {
        DateFormat dateFormat = getDateFormat();
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(dateString);
        } catch (ParseException ignored) {}

        Date now = new Date();
        return parsedDate != null && parsedDate.before(now);
    }

    private DateFormat getDateFormat() {
        Context context = mValidationContext.getContext();
        String dateFormatString =  mDateFormatResId != -1
                ? context.getString(mDateFormatResId) : mDateFormatString;
        return new SimpleDateFormat(dateFormatString);
    }
}
