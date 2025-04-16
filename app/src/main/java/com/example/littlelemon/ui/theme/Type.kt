import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.charcoal
import com.example.littlelemon.ui.theme.green

val KarlaRegular = FontFamily(
    Font(R.font.karla_regular)
)

val MarkaziRegular = FontFamily(
    Font(R.font.markazi_regular)
)

val bodyLarge = TextStyle(
    fontFamily = KarlaRegular,
    fontSize = 18.sp,
    fontWeight = FontWeight.Normal,
    color = charcoal
)
val title = TextStyle(
    fontFamily = KarlaRegular,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
)
val label = TextStyle(
    fontFamily = KarlaRegular,
    fontWeight = FontWeight.Medium,
    fontSize = 13.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
val welcome = TextStyle(
    fontFamily = KarlaRegular,
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold,
    letterSpacing = 0.1.em,
    color = Color.White
)
val h1 = TextStyle(
    fontFamily = MarkaziRegular,
    fontSize = 40.sp,
    fontWeight = FontWeight.Bold,
    letterSpacing = 0.1.em,
    color = charcoal
)
val h2 = TextStyle(
    fontFamily = KarlaRegular,
    color = charcoal,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold
)
val body1 = TextStyle(
    fontFamily = KarlaRegular,
    color = green
)
val button = TextStyle(
    fontFamily = KarlaRegular,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    letterSpacing = 0.1.em
)