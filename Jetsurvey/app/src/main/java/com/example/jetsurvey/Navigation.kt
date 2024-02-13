package com.example.jetsurvey

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetsurvey.Destinations.SIGN_IN_ROUTE
import com.example.jetsurvey.Destinations.SIGN_UP_ROUTE
import com.example.jetsurvey.Destinations.SURVEY_RESULTS_ROUTE
import com.example.jetsurvey.Destinations.SURVEY_ROUTE
import com.example.jetsurvey.Destinations.WELCOME_ROUTE
import com.example.jetsurvey.signinsignup.welcome.WelcomeRoute

/**
 * Navigationリスト
 */
object Destinations {
    const val WELCOME_ROUTE = "welcome"
    const val SIGN_UP_ROUTE = "signup/{email}"
    const val SIGN_IN_ROUTE = "signin/{email}"
    const val SURVEY_ROUTE = "survey"
    const val SURVEY_RESULTS_ROUTE = "surveyresults"
}

/**
 * Navigationをここに定義
 */
@Composable
fun JetsurveyNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = WELCOME_ROUTE
    ) {
        composable(WELCOME_ROUTE) {
            WelcomeRoute(
                onNavigateToSignIn = {
                    navController.navigate("signin/$it")
                },
                onNavigateToSignUp = {
                    navController.navigate("signup/$it")
                },
                onSignInAsGuest = {
                    navController.navigate(SURVEY_ROUTE)
                },
            )
        }
        composable(SIGN_IN_ROUTE) {
            // TODO
        }

        composable(SIGN_UP_ROUTE) {
            // TODO
        }

        composable(SURVEY_ROUTE) {
            // TODO
        }

        composable(SURVEY_RESULTS_ROUTE) {
            // TODO
        }
    }
}