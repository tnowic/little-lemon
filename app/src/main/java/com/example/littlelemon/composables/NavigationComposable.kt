import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.Home
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.Onboarding
import com.example.littlelemon.Profile
import com.example.littlelemon.RegistrationUtil.getRegisteredUser
import com.example.littlelemon.composables.Home
import com.example.littlelemon.composables.Onboarding
import com.example.littlelemon.composables.Profile


@Composable
fun NavigationComposable(
    navController: NavHostController,
    menuItemsLiveData: LiveData<List<MenuItemRoom>>
) {
    NavHost(
        navController = navController,
        startDestination = if (getRegisteredUser(LocalContext.current).isUserValid()) Home.route else Onboarding.route
    ) {
        composable(Home.route) {
            Home(navController, menuItemsLiveData)
        }
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}




