package id.faizalempe.carousellnews.domain.model


import java.util.concurrent.TimeUnit

data class News(
    val id: String?,
    val title: String?,
    val desc: String?,
    val bannerUrl: String?,
    val timeCreated: Long?,
    val rank: Long?
) {
    val timeAgo: CharSequence get() =
        timeCreated?.let { time ->
            val now = System.currentTimeMillis()
            val diff = now - (TimeUnit.SECONDS.toMillis(time))
            val diffMins = diff / TimeUnit.MINUTES.toMillis(1)
            val diffHours = diff / TimeUnit.HOURS.toMillis(1)
            val diffDays = diff / TimeUnit.DAYS.toMillis(1)
            val diffMonths = diff / TimeUnit.DAYS.toMillis(30)
            val diffYears = diff / TimeUnit.DAYS.toMillis(365)
            val isDiffYears = diffYears > 0
            val isDiffMonths = diffMonths > 0 && diffYears < 1
            val isDiffDays = diffDays > 0 && diffMonths < 1
            val isDiffHours = diffHours > 0 && diffDays < 1
            val isDiffMins = diffMins > 0 && diffHours < 1
            when {
                isDiffYears -> "$diffYears years ago"
                isDiffMonths -> "${(diffMonths - diffYears / 12)} months ago"
                isDiffDays -> "${(diffDays - diffMonths / 30)} days ago"
                isDiffHours -> "${(diffHours - diffDays * 24)} hours ago"
                isDiffMins -> "${(diffMins - diffHours * 60)} minutes ago"
                else -> "Just now"
            }
        }.orEmpty()
}