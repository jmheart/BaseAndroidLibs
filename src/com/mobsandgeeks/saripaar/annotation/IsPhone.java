package com.mobsandgeeks.saripaar.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mobsandgeeks.saripaar.rule.IsPhoneRule;
/*
**
* @author Ragunath Jawahar {@literal <rj@mobsandgeeks.com>}
* @since 1.0
*/
@ValidateUsing(IsPhoneRule.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsPhone {
   int sequence()      default -1;
   int messageResId()  default -1;
   String message()    default "手机号码不合法";
}
