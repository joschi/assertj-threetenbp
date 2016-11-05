/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.threeten.api;


import org.junit.Test;
import org.threeten.bp.LocalDateTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Tests for <code>{@link Assertions#assertThat(LocalDateTime)}</code>.
 * 
 * @author Joel Costigliola
 * @author Marcin Zajączkowski
 */
public class Assertions_assertThat_with_LocalDateTime_Test {

  @Test
  public void should_create_Assert() {
    LocalDateTimeAssert assertions = Assertions.assertThat(LocalDateTime.now());
    assertNotNull(assertions);
  }

  @Test
  public void should_pass_actual() {
    LocalDateTime localDateTime = LocalDateTime.now();
    LocalDateTimeAssert assertions = Assertions.assertThat(localDateTime);
    assertSame(localDateTime, assertions.getActual());
  }
}
