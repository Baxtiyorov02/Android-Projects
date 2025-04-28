package com.example.learningcompose.infornation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ConsulateDetailScreen(consulate: Consulate) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(12.dp)
        ) {

            // Rasm
            Image(
                painter = painterResource(id = consulate.imageResId),
                contentDescription = "${consulate.name} rasmi",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(6.dp, shape = RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    // Nomi
                    Text(
                        text = consulate.name,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Masofa (chiroyli shaklda)
                    consulate.distance?.let { distance ->
                        val formattedDistance = formatDistance(distance)
                        Text(
                            text = "📏 Masofa: $formattedDistance",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // Manzil
                    Text(
                        text = "📍 Manzil: ${consulate.address}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Telefon raqamlari
                    Text(
                        text = "📞 Telefon raqamlari:",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    consulate.phoneNumbers.forEach { number ->
                        Text(
                            text = number,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tavsif
                    Text(
                        text = consulate.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

/**
 * Masofani metr yoki kilometr shaklida chiroyli formatlab qaytaradi
 */
private fun formatDistance(distanceInMeters: Float): String {
    return if (distanceInMeters >= 1000) {
        String.format("%.1f km", distanceInMeters / 1000)
    } else {
        "${distanceInMeters.toInt()} m"
    }
}
