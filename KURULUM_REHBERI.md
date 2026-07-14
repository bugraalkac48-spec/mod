# Film Karakterleri Modu - Kurulum Rehberi

Bu klasör, Minecraft Forge 1.20.1 için hazırlanmış bir mod **kaynak kodudur**.
Yani içinde direkt çalışan bir `.jar` yok — önce senin bilgisayarında **derlemen**
gerekiyor. Adımlar aşağıda.

## Modun ne yaptığı
- 10 farklı "film karakteri" entity'si (her biri `KarakterData.java` içinde tanımlı)
- Ekmekle evcilleştirme (köpek gibi)
- Evcil karaktere sağ tıklayınca rastgele bir replik söylüyor
- Chat'e `!replik` yazınca yakındaki tüm evcil karakterler konuşuyor
- Normal chat yazarken de %15 ihtimalle yakındaki karakterler rastgele tepki veriyor
- Her karakter için ayrı bir "çağırma taşı" item'ı var (bloğa sağ tıklayınca o karakteri spawnlıyor)

## ÖNEMLİ - Senin doldurman gerekenler
1. **Replikler**: `src/main/java/com/filmkarakterleri/entity/KarakterData.java`
   içindeki `phrases` listeleri şu an yer tutucu (placeholder) genel cümlelerle dolu.
   Ben filmlerden birebir replik yazamıyorum (telif hakkı). Bu listeleri açıp
   istediğin esprili cümlelerle değiştirebilirsin.
2. **Texture (görsel)**: `src/main/resources/assets/filmkarakterleri/textures/entity/`
   klasörüne `karakter_1.png` ... `karakter_10.png` isimli, 64x64 boyutunda,
   Minecraft oyuncu skin formatında PNG dosyaları koyman lazım. Boş bırakırsan
   mor-siyah "eksik texture" görünür ama mod yine de çalışır.
3. **İsimler**: `KarakterData.java` içindeki `displayName` alanlarını istediğin
   gibi değiştirebilirsin (örn. "Recep İvedik", "Şaban" vs.) - karakter isimleri
   kendisi genelde sorun teşkil etmez, asıl dikkat edilmesi gereken film
   diyaloglarının birebir kopyalanmaması.

## Derleme Adımları (Windows/Mac/Linux hepsinde aynı mantık)

### 1) Gereken programlar
- **Java 17** (JDK) - https://adoptium.net adresinden indir
- Bir IDE önerilir: **IntelliJ IDEA (Community)** ya da **Eclipse**
- İnternet bağlantısı (Gradle, Forge kütüphanelerini otomatik indirecek)

### 2) Projeyi aç
1. Bu klasörü (`FilmKarakterleriMod`) bilgisayarına kopyala.
2. IntelliJ IDEA'yı aç → `Open` → bu klasörü seç.
3. IDE, `build.gradle` dosyasını görüp otomatik olarak Gradle senkronizasyonu
   başlatacak. İlk sefer 5-15 dakika sürebilir (Forge indiriliyor).

### 3) Test etmek (opsiyonel ama önerilir)
Terminal/komut satırında proje klasöründeyken:
```
./gradlew runClient        (Mac/Linux)
gradlew.bat runClient      (Windows)
```
Bu, modun yüklü olduğu bir Minecraft istemcisini açar. Yaratıcı modda
`/give @s filmkarakterleri:cagir_karakter_1` yazarak test edebilirsin.

### 4) Jar dosyasını üretmek
```
./gradlew build            (Mac/Linux)
gradlew.bat build          (Windows)
```
Derleme bitince jar dosyan şurada olacak:
```
build/libs/filmkarakterleri-1.0.0.jar
```

### 5) TLauncher'da çalıştırma
1. TLauncher'ı aç.
2. Sol üstten profil seçimine git, **Forge 1.20.1** profili oluştur
   (TLauncher "Forge" sekmesinden 1.20.1'i seçip indirt).
3. Bu profille bir kere normal oyunu aç ki `.minecraft/mods` klasörü oluşsun,
   sonra kapat.
4. `.minecraft/mods` klasörünü bul:
   - Windows: `%appdata%\.minecraft\mods`
   - Mac: `~/Library/Application Support/minecraft/mods`
5. Ürettiğin `filmkarakterleri-1.0.0.jar` dosyasını bu `mods` klasörüne kopyala.
6. TLauncher'dan Forge 1.20.1 profiliyle oyunu başlat. Mod otomatik yüklenecek.

## Oyun içinde nasıl kullanılır
1. Yaratıcı modda envanterden ya da komutla çağırma taşlarından birini al:
   `/give @p filmkarakterleri:cagir_karakter_1`
2. Bir bloğa sağ tıkla → karakter spawn olsun.
3. Elinde ekmekle karaktere sağ tıkla, birkaç denemede evcilleşir (kalp/duman
   partikülü çıkar).
4. Evcilleşince:
   - Normal sağ tık → rastgele replik söyler
   - Shift + sağ tık → otur/kalk
   - Chat'e `!replik` yaz → yakındaki tüm evcil karakterler konuşur
   - Normal sohbet ederken de arada kendiliğinden lafa girer

## Notlar / sınırlamalar
- Bu bir **iskelet/temel sürüm**. Gerçek 3D karakter modelleri, sesler,
  animasyonlar eklemek istersen bunun üzerine geliştirme yapman gerekir
  (örn. Blockbench ile özel model, ses dosyaları için `.ogg` ekleme vb.)
- Film karakterlerinin görselini/sesini/diyaloglarını birebir kullanmak
  telif hakkı ihlaline girebilir; bu yüzden kişisel/özel kullanım dışında
  (örn. YouTube'da yayınlama, mod sitelerinde paylaşma) dikkatli olmalısın.
