package com.tufei.architecturedemo.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 在Dagger里，一个无范围的component不能依赖一个有范围的component。当{@link AppComponent}是一个有范围的component
 * 时{@code @Singleton}，我们要创建一个定制的scope，给所有的activity components使用。
 * 另外要注意，一个被标注一个规定的scope的component不能拥有一个被标注了相同scope的sub component(子组件)，
 * 不然编译时会报错。
 *
 * In Dagger, an unscoped component cannot depend on a scoped component. As
 * {@link AppComponent} is a scoped component ({@code @Singleton}, we create a custom
 * scope to be used by all fragment components. Additionally, a component with a specific scope
 * cannot have a sub component with the same scope.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
