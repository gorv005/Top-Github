package com.appstreet.top_github.interfaces

import androidx.annotation.IntDef
import com.appstreet.top_github.utils.CommonInt.BLOCKED_OR_NEVER_ASKED
import com.appstreet.top_github.utils.CommonInt.DENIED
import com.appstreet.top_github.utils.CommonInt.GRANTED
import kotlin.annotation.Retention
@Retention(AnnotationRetention.SOURCE)
@IntDef(GRANTED, DENIED, BLOCKED_OR_NEVER_ASKED)
annotation class PermissionStatus

