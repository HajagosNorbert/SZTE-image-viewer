# SZTE Image Viewer
Egyszerű JavaFX képnézegető program, amellyel képesek vagyunk egy képet megtekinteni és rajta elvégezhető egyszerű változtatásokat lementeni.

**Programozás I.** tehetséggondozó kurzus projekt.

### Csapattagok:
* Wolf Levente
* Molnár Ádám
* Hajagos Norbert

---
## Részletes specifikáció:

* **Asztali** program, **grafikus** megjelenítő felülettel.
* **Tallózás**: 1 konkrét kép kiválasztása a könyvtárból. Csak a képek jelenjenek meg a tallózáskor, tehát _png_, _jpg_, _bmp_ kiterjesztésűek. Több formátumot támogasson, legalább ezt a 3-at. 
* **Kép megjelenítés**: A betallózott képet meg kell, tudnia jeleníteni a programnak.
* **Egyszerű képfeldolgozás**: _tükrözés_, _forgatás 90 fokkal_, _színskála állítása_, amely közül minimum a fekete-fehérré alakítás szerepelnie kell benne.
* **Mentés**: A megváltoztatott képet lehessen menteni. Az eredeti ne legyen felülírva, helyette más néven lehessen elmenteni az új, átalakított képet.
* **Bővíthetőség**: Egy új _.class_ file beillesztésével az előírt helyre (akár a program futtatása közben is. Ez nem igény, csak előnyös) kibővíthetők legyenek a következők:
  1. Támogatott képformátumok listája (pl: _.giff_)
  2. Képszerkesztési műveletek listája (pl: _kicsinyítés_, _nagyítás_)
---

## Munkaterv

#### Hajagos Norbert
- [ ] Kép megnyitás
- [ ] Kép mentés

#### Wolf Levente
- [ ] Színskála

#### Molnár Ádám
- [ ] Nagyítás


## [Tervdokumentáció link](https://lucid.app/lucidchart/invitations/accept/1ed963ff-de0c-4eea-940d-d41ed3c0cab4)

## [Tesztjegyzőkönyv link](./TestReport.md)
