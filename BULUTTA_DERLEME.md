# Bilgisayarına Hiçbir Şey Kurmadan Modu Derleme (GitHub Actions)

Bu yöntemde Java, IntelliJ, Gradle gibi hiçbir şeyi **senin bilgisayarına**
kurmuyoruz. Derleme işlemini GitHub'ın ücretsiz sunucusu yapıyor, sen sadece
tarayıcıdan dosya yükleyip sonucu indiriyorsun.

## 1) Ücretsiz GitHub hesabı aç
- https://github.com adresine git, **Sign up** ile ücretsiz hesap oluştur
  (sadece e-posta yeterli, program kurmuyorsun).

## 2) Yeni bir repository (depo) oluştur
1. Giriş yaptıktan sonra sağ üstten **+** → **New repository**.
2. İsim ver (örn. `film-karakterleri-mod`).
3. **Private** ya da **Public** seçebilirsin, fark etmez.
4. **Create repository** de.

## 3) Proje dosyalarını yükle (hiç kod yazmadan, tarayıcıdan sürükle-bırak)
1. Az önce açılan boş repo sayfasında **"uploading an existing file"**
   linkine tıkla (ya da **Add file → Upload files**).
2. Bilgisayarında `FilmKarakterleriMod.zip` dosyasını **önce çıkart** (extract et),
   içindeki TÜM dosya ve klasörleri (gizli `.github` klasörü dahil!) seç,
   tarayıcıya sürükle-bırak.
   - ÖNEMLİ: `.github` klasörü gizli görünebilir, dosya gezgininde "gizli
     dosyaları göster" seçeneğini aç ki onu da sürükleyebilesin.
3. Alt kısımda **Commit changes** butonuna bas.

## 4) Otomatik derlemeyi izle
1. Repo sayfasında üstteki **Actions** sekmesine tıkla.
2. "Mod Derle" adında bir işlemin çalıştığını göreceksin (birkaç dakika sürer).
3. Yeşil tik ✅ çıkınca derleme başarılı demektir.

## 5) Derlenen jar dosyasını indir
1. Aynı Actions sayfasında, tamamlanan çalışmanın üzerine tıkla.
2. Sayfanın altında **Artifacts** bölümünde `filmkarakterleri-mod` adında
   bir zip göreceksin, ona tıklayıp indir.
3. İndirdiğin zip'i aç, içinden `.jar` dosyasını çıkar.

## 6) TLauncher'a yerleştir
1. TLauncher'da Forge 1.20.1 profilini oluştur (daha önce anlattığım gibi),
   bir kere açıp kapat ki `mods` klasörü oluşsun.
2. `%appdata%\.minecraft\mods` klasörünü aç.
3. İndirdiğin `.jar` dosyasını bu klasöre kopyala.
4. TLauncher'dan Forge profiliyle oyunu başlat.

## Özet - hiç program kurmadın, sadece:
- Tarayıcıdan GitHub hesabı açtın
- Dosyaları sürükle-bıraktın
- GitHub'ın sunucusu derledi
- Sonucu indirip mods klasörüne koydun

## Not
Kod içinde bir hata çıkarsa (kırmızı ❌ işareti), Actions sayfasındaki
"build" adımına tıklayıp hata metnini kopyalayıp bana gönderebilirsin,
birlikte düzeltiriz.
