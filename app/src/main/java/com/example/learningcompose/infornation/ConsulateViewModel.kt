package com.example.learningcompose.infornation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.learningcompose.R

class ConsulateViewModel:ViewModel() {
    val consulateList = listOf(
        Consulate(
            name = "O‘zbekiston Konsulligi – Moskva",
            address = "Moskvadagi O‘zbekiston elchixonasi, Moskva, Rossiya",
            phoneNumbers = listOf("+7 (495) 123-45-67", "+7 (495) 765-43-21"),
            imageResId = R.drawable.rossiya,
            description = "O‘zbekiston fuqarolariga huquqiy va konsullik xizmatlarini ko‘rsatadi. O‘zbekiston Respublikasi fuqarolarini konsullik ro‘yxatiga olish, pasport va ID-kartalarni yangilash, huquqiy maslahatlar berish, turli hujjatlarni tasdiqlash va boshqa konsullik xizmatlari taqdim etiladi."
        ),
        Consulate(
            name = "O‘zbekiston Konsulligi – London",
            address = "52-54, Notting Hill Gate, London, Buyuk Britaniya",
            phoneNumbers = listOf("+44 20 7229 1188", "+44 20 7229 1189"),
            imageResId = R.drawable.london,
            description = "O‘zbekiston fuqarolariga konsullik yordamini taqdim etadi. Konsullik pasportlari va hujjatlarni tasdiqlash, turli ruxsatnomalar va visa xizmatlarini taqdim etadi. Shuningdek, O‘zbekiston fuqarolari uchun huquqiy va tashkiliy yordam ko‘rsatiladi."
        ),
        Consulate(
            name = "O‘zbekiston Konsulligi – Istanbul",
            address = "Büyükdere Caddesi, No: 25, Şişli, Istanbul, Turkiya",
            phoneNumbers = listOf("+90 212 343 55 00", "+90 212 343 55 01"),
            imageResId = R.drawable.istambul,
            description = "O‘zbekiston fuqarolariga visa va konsullik xizmatlarini taqdim etadi. Konsullik pasportlari, hujjatlarni tasdiqlash, o‘qish va ish vizalari berish, shuningdek, huquqiy yordam va boshqa xizmatlar ko‘rsatiladi."
        ),
        Consulate(
            name = "O‘zbekiston Konsulligi – New York",
            address = "800 2nd Ave, Suite 400, New York, NY, AQSh",
            phoneNumbers = listOf("+1 212 979 1555", "+1 212 979 1544"),
            imageResId = R.drawable.new_york,
            description = "O‘zbekiston fuqarolariga konsullik xizmatlari va huquqiy yordam ko‘rsatiladi. Pasport yangilash, o‘qish vizalari, turli hujjatlarni tasdiqlash, hamda boshqa davlatlar bilan aloqalar bo‘yicha maslahatlar berish uchun xizmat ko‘rsatiladi."
        ),
        Consulate(
            name = "O‘zbekiston Konsulligi – Abu Dabi",
            address = "10th Floor, Capital Plaza Tower, Abu Dabi, Birlashgan Arab Amirliklari",
            phoneNumbers = listOf("+971 2 444 8033", "+971 2 444 8034"),
            imageResId = R.drawable.abu_dabi,
            description = "O‘zbekiston fuqarolariga turli konsullik xizmatlarini, masalan, pasport yangilash, tasdiqlash xizmatlari, turistik vizalar va boshqa hujjatlarni taqdim etadi. Shuningdek, fuqarolar uchun huquqiy maslahatlar va yordam ko‘rsatiladi."
        ),
        Consulate(
            name = "O‘zbekiston Konsulligi – Berlinda",
            address = "Fasanenstraße 69, 10719 Berlin, Germaniya",
            phoneNumbers = listOf("+49 30 897 890", "+49 30 897 891"),
            imageResId = R.drawable.berlin,
            description = "O‘zbekiston fuqarolariga konsullik xizmatlarini ko‘rsatadi. Pasport va hujjatlarni yangilash, huquqiy yordam berish, turli turistik va ish vizalarini rasmiylashtirish xizmatlari taqdim etiladi. Fuqarolar uchun yordam va maslahatlar, shuningdek, o‘zaro savdo-sotiq masalalarida qo‘llab-quvvatlash ko‘rsatiladi."
        ),
        Consulate(
            name = "O‘zbekiston Konsulligi – Nur-Sulton (Astana)",
            address = "Kabanbay Batyr Avenue, 28, Nur-Sulton, Qozog‘iston",
            phoneNumbers = listOf("+7 7172 24 02 00", "+7 7172 24 02 01"),
            imageResId = R.drawable.qozoq,
            description = "Nur-Sultondagi O‘zbekiston Konsulligi O‘zbekiston fuqarolari va Qozog‘istonda yashovchi vatandoshlar uchun keng ko‘lamli konsullik xizmatlarini taqdim etadi. Bular qatoriga pasportlarni yangilash, tug‘ilganlik va nikohni ro‘yxatdan o‘tkazish, notarial xizmatlar, vizalar bilan bog‘liq masalalar, fuqarolik holati dalolatnomalarini rasmiylashtirish hamda huquqiy maslahatlar kiradi."
        ),
        Consulate(
            name = "O‘zbekiston Konsulligi – Doha",
            address = "West Bay, Doha, Qatar",
            phoneNumbers = listOf("+974 443 669 98", "+974 443 669 99"),
            imageResId = R.drawable.dubay,
            description = "O‘zbekiston fuqarolariga konsullik xizmatlarini ko‘rsatadi. Pasportlarni yangilash, viza xizmatlari, hujjatlarni tasdiqlash, shuningdek, O‘zbekiston fuqarolari uchun yuridik yordam taqdim etiladi."
        ),
        Consulate(
            name = "O‘zbekiston Konsulligi – Dubai",
            address = "Al Qusais, Dubai, Birlashgan Arab Amirliklari",
            phoneNumbers = listOf("+971 4 251 15 95", "+971 4 251 15 96"),
            imageResId = R.drawable.dubay,
            description = "O‘zbekiston fuqarolariga konsullik xizmatlari taqdim etadi, jumladan, pasport va ID-kartalarni yangilash, turli hujjatlarni tasdiqlash, viza xizmatlari va huquqiy yordam ko‘rsatish."
        ),
        Consulate(
            name = "O‘zbekiston Konsulligi – Frankfurt",
            address = "Kaiserstr. 45, 60329 Frankfurt am Main, Germaniya",
            phoneNumbers = listOf("+49 69 718 882", "+49 69 718 883"),
            imageResId = R.drawable.frankfurt,
            description = "O‘zbekiston fuqarolariga konsullik xizmatlari ko‘rsatiladi. Pasport va ID-kartalar yangilash, hujjatlarni tasdiqlash, turli vizalar va boshqa konsullik yordamlarini taqdim etish."
        )
    )

    private val _selectedConsulate = mutableStateOf<Consulate?>(null)
    val selectedConsulate: State<Consulate?> = _selectedConsulate

    fun getConsulates(): List<Consulate> = consulateList

    fun selectConsulate(consulate: Consulate) {
        _selectedConsulate.value = consulate
    }


}