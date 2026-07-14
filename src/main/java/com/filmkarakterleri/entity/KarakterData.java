package com.filmkarakterleri.entity;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

/**
 * 10 karakter için temel veri tanımı.
 *
 * ÖNEMLİ: Buradaki "phrases" (repliker) listesi BİLEREK yer tutucu / genel
 * ifadelerle doldurulmuştur. Film senaryolarından birebir alınmış replikleri
 * ben üretemem (telif hakkı). İstersen bu listeleri kendi beğendiğin, kendi
 * yazdığın esprili cümlelerle doldurabilirsin - kod tarafı zaten hazır,
 * sadece aşağıdaki String dizilerini değiştirmen yeterli.
 *
 * "texture" alanındaki dosya yollarını da kendi hazırladığın PNG texture'larla
 * eşleştirmen gerekiyor (64x64 humanoid formatında, Minecraft skin şablonu gibi).
 * Dosyaları şuraya koy:
 * src/main/resources/assets/filmkarakterleri/textures/entity/<dosya_adi>.png
 */
public enum KarakterData {

    KARAKTER_1("karakter_1", "Aksiyoncu Amca", "karakter_1.png", List.of(
            "Sen kimsin ya?!",
            "Ben buradayım artık, tamam mı?",
            "Yok öyle bir şey!"
    )),
    KARAKTER_2("karakter_2", "Mahalle Kabadayısı", "karakter_2.png", List.of(
            "Bana bak sen, işine bak!",
            "Ne oluyor burada?",
            "Hepsi bana borçlu bu mahallenin."
    )),
    KARAKTER_3("karakter_3", "Saf Köylü", "karakter_3.png", List.of(
            "Vay be, şehir çok kalabalıkmış.",
            "Ben bunu anlamadım valla.",
            "Aa bu ne be böyle?"
    )),
    KARAKTER_4("karakter_4", "Kurnaz Esnaf", "karakter_4.png", List.of(
            "Al bakalım, senden para almam!",
            "Bu işte bir bit yeniği var.",
            "Ucuza kapattık yine."
    )),
    KARAKTER_5("karakter_5", "Emekli Öğretmen", "karakter_5.png", List.of(
            "Otur yerine, ders başlıyor!",
            "Bu nasıl bir cevap böyle?",
            "Gençlik nereye gidiyor bilmiyorum."
    )),
    KARAKTER_6("karakter_6", "Şehirli Delikanlı", "karakter_6.png", List.of(
            "Aynen öyle kanka.",
            "Ciddi misin sen şu an?",
            "Hadi be, inanmıyorum!"
    )),
    KARAKTER_7("karakter_7", "Taşralı Amca", "karakter_7.png", List.of(
            "Buraların işi başka be evladım.",
            "Bizim köyde böyle olmazdı.",
            "Otur şöyle, çay demleyeyim."
    )),
    KARAKTER_8("karakter_8", "Hayalperest Genç", "karakter_8.png", List.of(
            "Bir gün ben de başaracağım!",
            "Bu iş bende, merak etmeyin.",
            "Hayaller gerçek olur mu dersin?"
    )),
    KARAKTER_9("karakter_9", "Sinirli Patron", "karakter_9.png", List.of(
            "Bu ne iş böyle ya!",
            "Herkes toplansın, acil durum var!",
            "İşimi doğru yapın diyorum!"
    )),
    KARAKTER_10("karakter_10", "Şanssız Kahraman", "karakter_10.png", List.of(
            "Yine mi başıma geldi bu?",
            "Bir bu eksikti zaten.",
            "Neyse, alışkınım artık."
    ));

    private final String id;
    private final String displayName;
    private final String textureFile;
    private final List<String> phrases;

    KarakterData(String id, String displayName, String textureFile, List<String> phrases) {
        this.id = id;
        this.displayName = displayName;
        this.textureFile = textureFile;
        this.phrases = phrases;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ResourceLocation getTexture() {
        return new ResourceLocation("filmkarakterleri", "textures/entity/" + textureFile);
    }

    public List<String> getPhrases() {
        return phrases;
    }

    public String getRandomPhrase() {
        return phrases.get((int) (Math.random() * phrases.size()));
    }

    public static KarakterData byIndex(int index) {
        KarakterData[] values = values();
        if (index < 0 || index >= values.length) {
            return values[0];
        }
        return values[index];
    }
}
