package fr.amanin.demo.spring.ktctxreceiverdemo

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@SpringBootApplication
open class KtCtxReceiverDemoApplication

fun main(args: Array<String>) {
	runApplication<KtCtxReceiverDemoApplication>(*args)
}

@Component
class AppInfo(@Value("\${spring.application.name}") val name: String)

context(AppInfo)
open class ContextBased(val source: String) {
	init {
		println(
			"""
            >>
            >> Injected from $source: $name
            >>
            """.trimIndent()
		)
	}
}

@Configuration
open class ByConfiguration {
	context(AppInfo)
	@Bean("from-configuration")
	open fun create(): ContextBased = ContextBased("configuration")
}

context(AppInfo)
@Component
class ByConstructor() : ContextBased("constructor")
