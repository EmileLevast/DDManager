package fr.emile.ddmanager.mainClass

import androidx.room.Embedded
import fr.emile.ddmanager.Container
import fr.emile.ddmanager.R

enum class StuffType{
    ARME,ARTEFACT,SORT,POTION,TRAP;
}
class StuffCard(nom:String, var type:StuffType, imgId:Int):Entity(imgId,nom)
{
    var isUsed=false

    override fun toKey()=nom

    constructor():this("card_unkown",StuffType.ARTEFACT,0)//only for Room

    companion object {
        val CardReference= Container(

                //potion
                StuffCard("potion_de_restauration_partielle",StuffType.POTION, R.drawable.potion_de_restauration_partielle),
                StuffCard("potion_arret_du_temps",StuffType.POTION, R.drawable.potion_arret_du_temps),
                StuffCard("potion_attaque_soudaine",StuffType.POTION, R.drawable.potion_attaque_soudaine),
                StuffCard("potion_de_benediction_de_kord",StuffType.POTION, R.drawable.potion_de_benediction_de_kord),
                StuffCard("potion_de_cercle_de_guerison",StuffType.POTION, R.drawable.potion_de_cercle_de_guerison),
                StuffCard("potion_de_faiblesse",StuffType.POTION, R.drawable.potion_de_faiblesse),
                StuffCard("potion_de_faiblesse_supreme",StuffType.POTION, R.drawable.potion_de_faiblesse_supreme),
                StuffCard("potion_de_fou_rire",StuffType.POTION, R.drawable.potion_de_fou_rire),
                StuffCard("potion_de_main_imperieuse",StuffType.POTION, R.drawable.potion_de_main_imperieuse),
                StuffCard("potion_de_montee_adrenaline",StuffType.POTION, R.drawable.potion_de_montee_adrenaline),
                StuffCard("potion_de_restauration",StuffType.POTION, R.drawable.potion_de_restauration),
                StuffCard("potion_de_restauration_supreme",StuffType.POTION, R.drawable.potion_de_restauration_supreme),
                StuffCard("potion_de_sagesse_olidammara",StuffType.POTION, R.drawable.potion_de_sagesse_olidammara),
                StuffCard("potion_de_soins_important",StuffType.POTION, R.drawable.potion_de_soins_important),
                StuffCard("potion_de_soins_moderes",StuffType.POTION, R.drawable.potion_de_soins_moderes),
                StuffCard("potion_initiative",StuffType.POTION, R.drawable.potion_initiative),
                StuffCard("potion_introspection",StuffType.POTION, R.drawable.potion_introspection),
                StuffCard("potion_ombre_fumigene",StuffType.POTION, R.drawable.potion_ombre_fumigene),
                StuffCard("potion_soins_leger",StuffType.POTION, R.drawable.potion_soins_leger),

                //Arme
                StuffCard("arbalete_de_la_foi",StuffType.ARME, R.drawable.arbalete_de_la_foi),
                StuffCard("arbalete_sacree_de_pelor",StuffType.ARME, R.drawable.arbalete_sacree_de_pelor),
                StuffCard("arc_beni_des_elfes",StuffType.ARME, R.drawable.arc_beni_des_elfes),
                StuffCard("arc_court_des_anciens",StuffType.ARME, R.drawable.arc_court_des_anciens),
                StuffCard("arc_de_liberte",StuffType.ARME, R.drawable.arc_de_liberte),
                StuffCard("arc_long_composite",StuffType.ARME, R.drawable.arc_long_composite),
                StuffCard("arc_long_des_seigneurs_elfes",StuffType.ARME, R.drawable.arc_long_des_seigneurs_elfes),
                StuffCard("arc_petrificateur_de_medusa",StuffType.ARME, R.drawable.arc_petrificateur_de_medusa),
                StuffCard("arc_phenix",StuffType.ARME, R.drawable.arc_phenix),
                StuffCard("bolas_impitoyables",StuffType.ARME, R.drawable.bolas_impitoyables),
                StuffCard("bombe_flash",StuffType.ARME, R.drawable.bombe_flash),
                StuffCard("dague_de_lance_equilibre",StuffType.ARME, R.drawable.dague_de_lance_equilibre),
                StuffCard("epee_a_deux_mains",StuffType.ARME, R.drawable.epee_a_deux_mains),
                StuffCard("potion de restauration partielle",StuffType.ARME, R.drawable.epee_des_rois_proscrits),
                StuffCard("epee_large",StuffType.ARME, R.drawable.epee_large),
                StuffCard("epee_large_a_deux_mains",StuffType.ARME, R.drawable.epee_large_a_deux_mains),
                StuffCard("epee_tourmentee_esclavage",StuffType.ARME, R.drawable.epee_tourmentee_esclavage),
                StuffCard("fendeur_de_cranes",StuffType.ARME, R.drawable.fendeur_de_cranes),
                StuffCard("fendoir_a_os",StuffType.ARME, R.drawable.fendoir_a_os),
                StuffCard("fidele_hache_de_forge_naine",StuffType.ARME, R.drawable.fidele_hache_de_forge_naine),
                StuffCard("fureur_du_dragon",StuffType.ARME, R.drawable.fureur_du_dragon),
                StuffCard("gantelet_de_misere",StuffType.ARME, R.drawable.gantelet_de_misere),
                StuffCard("hache_de_fureur_du_nord",StuffType.ARME, R.drawable.hache_de_fureur_du_nord),
                StuffCard("hache_du_maitre",StuffType.ARME, R.drawable.hache_du_maitre),
                StuffCard("hache_perche",StuffType.ARME, R.drawable.hache_perche),
                StuffCard("heraut_de_la_douleur",StuffType.ARME, R.drawable.heraut_de_la_douleur),
                StuffCard("hurleur",StuffType.ARME, R.drawable.hurleur),
                StuffCard("le_liberateur",StuffType.ARME, R.drawable.le_liberateur),
                StuffCard("marteau_de_liberte",StuffType.ARME, R.drawable.marteau_de_liberte),
                StuffCard("marteau_de_soumission",StuffType.ARME, R.drawable.marteau_de_soumission),
                StuffCard("masse_arme_de_la_delivrance",StuffType.ARME, R.drawable.masse_arme_de_la_delivrance),
                StuffCard("masse_arme_de_la_foi",StuffType.ARME, R.drawable.masse_arme_de_la_foi),
                StuffCard("porte_mort",StuffType.ARME, R.drawable.porte_mort),
                StuffCard("sarbacane_empoisonnee",StuffType.ARME, R.drawable.sarbacane_empoisonnee),
                StuffCard("serviteur_insoumis_de_kord",StuffType.ARME, R.drawable.serviteur_insoumis_de_kord),
                StuffCard("shurikens_explosifs",StuffType.ARME, R.drawable.shurikens_explosifs),
                StuffCard("vrille_du_tourment",StuffType.ARME, R.drawable.vrille_du_tourment),

                //Sort
                StuffCard("arme_spirituelle",StuffType.SORT, R.drawable.arme_spirituelle),
                StuffCard("bouclier_energie",StuffType.SORT, R.drawable.bouclier_energie),
                StuffCard("boule_de_feu",StuffType.SORT, R.drawable.boule_de_feu),
                StuffCard("brume_de_dissimulation",StuffType.SORT, R.drawable.brume_de_dissimulation),
                StuffCard("cage_de_force",StuffType.SORT, R.drawable.cage_de_force),
                StuffCard("cercle_de_guerison",StuffType.SORT, R.drawable.cercle_de_guerison),
                StuffCard("cercle_guerison_partielle",StuffType.SORT, R.drawable.cercle_guerison_partielle),
                StuffCard("colonne_de_feu",StuffType.SORT, R.drawable.colonne_de_feu),
                StuffCard("cone_de_froid",StuffType.SORT, R.drawable.cone_de_froid),
                StuffCard("eclair",StuffType.SORT, R.drawable.eclair),
                StuffCard("eclair_multiple",StuffType.SORT, R.drawable.eclair_multiple),
                StuffCard("fleche_acide_de_melf",StuffType.SORT, R.drawable.fleche_acide_de_melf),
                StuffCard("fleches_enflammees",StuffType.SORT, R.drawable.fleches_enflammees),
                StuffCard("mains_enflammees",StuffType.SORT, R.drawable.mains_enflammees),
                StuffCard("poing_de_bigby",StuffType.SORT, R.drawable.poing_de_bigby),
                StuffCard("projectile_magique",StuffType.SORT, R.drawable.projectile_magique),
                StuffCard("rayon_de_givre",StuffType.SORT, R.drawable.rayon_de_givre),
                StuffCard("restauration_supreme",StuffType.SORT, R.drawable.restauration_supreme),
                StuffCard("serviteur_invisible",StuffType.SORT, R.drawable.serviteur_invisible),
                StuffCard("tempete_de_feu",StuffType.SORT, R.drawable.tempete_de_feu),
                StuffCard("tempete_de_glace",StuffType.SORT, R.drawable.tempete_de_glace),


                //artefact
                StuffCard("amulette_de_yondalla",StuffType.ARTEFACT, R.drawable.amulette_de_yondalla),
                StuffCard("fortune_de_yondalla",StuffType.ARTEFACT, R.drawable.fortune_de_yondalla),
                StuffCard("amulette_olidammara",StuffType.ARTEFACT, R.drawable.amulette_olidammara),
                StuffCard("anneau_des_ombres",StuffType.ARTEFACT, R.drawable.anneau_des_ombres),
                StuffCard("bouclier_du_chaos",StuffType.ARTEFACT, R.drawable.bouclier_du_chaos),
                StuffCard("bouclier_mirroir_elfe",StuffType.ARTEFACT, R.drawable.bouclier_mirroir_elfe),
                StuffCard("cape_de_boccob",StuffType.ARTEFACT, R.drawable.cape_de_boccob),
                StuffCard("cape_en_peau_ecorce",StuffType.ARTEFACT, R.drawable.cape_en_peau_ecorce),
                StuffCard("cor_de_invocateur",StuffType.ARTEFACT, R.drawable.cor_de_invocateur),
                StuffCard("orbe_de_vision_lucide",StuffType.ARTEFACT, R.drawable.orbe_de_vision_lucide),

                //pieges
                StuffCard("appel_de_la_tombe",StuffType.TRAP, R.drawable.appel_de_la_tombe),
                StuffCard("brume_etouffante",StuffType.TRAP, R.drawable.brume_etouffante),
                StuffCard("couverture_de_flammes",StuffType.TRAP, R.drawable.couverture_de_flammes),
                StuffCard("lumiere_aveuglante",StuffType.TRAP, R.drawable.lumiere_aveuglante),
                StuffCard("perte_de_magie",StuffType.TRAP, R.drawable.perte_de_magie),
                StuffCard("trahison_brutale",StuffType.TRAP, R.drawable.trahison_brutale),
                StuffCard("voix_des_damnes",StuffType.TRAP, R.drawable.voix_des_damnes)

                )
    }



}
