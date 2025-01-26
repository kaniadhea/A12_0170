package com.example.projectakhir.ui.navigasi

import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics.composable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectakhir.ui.View.Siswa.*
import com.example.projectakhir.ui.View.Instruktur.*
import com.example.projectakhir.ui.View.Pendaftaran.*

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
//    NavHost(
//        navController = navController,
//        startDestination = DestinasiHomeSiswa.route,
//        modifier = modifier,
//    ) {
//        // Siswa
//        composable(DestinasiHomeSiswa.route) {
//            HomeScreenSiswa(
//                navigateTolItemEntry = { navController.navigate(DestinasiEntrySiswa.route) },
//                onDetailClick = { id_siswa ->
//                    navController.navigate("${DestinasiDetailSiswa.route}/$id_siswa") {
//                        popUpTo(DestinasiHomeSiswa.route) { inclusive = true }
//                    }
//                }
//            )
//        }
//        composable(DestinasiEntrySiswa.route) {
//            EntrySwaScreen(navigateBack = {
//                navController.navigate(DestinasiHomeSiswa.route) {
//                    popUpTo(DestinasiHomeSiswa.route) { inclusive = true }
//                }
//            })
//        }
//        composable(DestinasiDetailSiswa.routeWithArg) { backStackEntry ->
//            val id_siswa =
//                backStackEntry.arguments?.getString(DestinasiDetailSiswa.ID_Siswa)?.toIntOrNull()
//            id_siswa?.let {
//                DetailSiswaView(
//                    id_siswa = it,
//                    navigateBack = {
//                        navController.navigate(DestinasiHomeSiswa.route) {
//                            popUpTo(DestinasiHomeSiswa.route) { inclusive = true }
//                        }
//                    },
//                    onEditClick = {
//                        navController.navigate("${DestinasiUpdateSiswa.route}/$it")
//                    }
//                )
//            }
//        }
//        composable(
//            DestinasiUpdateSiswa.routesWithArg,
//            arguments = listOf(navArgument(DestinasiUpdateSiswa.ID_Siswa) {
//                type = NavType.IntType // Ubah menjadi NavType.IntType untuk ID bertipe Int
//            })
//        ) { backStackEntry ->
//            val id_siswa = backStackEntry.arguments?.getInt(DestinasiUpdateSiswa.ID_Siswa)
//            id_siswa?.let {
//                UpdateSiswaView(
//                    navigateBack = { navController.popBackStack() },
//                    modifier = modifier
//                )
//            }
//        }
//    }


    //Instruktur
//    NavHost(
//        navController = navController,
//        startDestination = DestinasiHomeInstruktur.route,
//        modifier = modifier,
//    ) {
//        composable(DestinasiHomeInstruktur.route) {
//            HomeScreenInstruktur(
//                navigateTolItemEntry = { navController.navigate(DestinasiEntryInstruktur.route) },
//                onDetailClick = { id_instruktur ->
//                    navController.navigate("${DestinasiDetailInstruktur.route}/$id_instruktur") {
//                        popUpTo(DestinasiHomeInstruktur.route) { inclusive = true }
//                    }
//                }
//            )
//        }
//        composable(DestinasiEntryInstruktur.route) {
//            EntryInstruScreen(navigateBack = {
//                navController.navigate(DestinasiHomeInstruktur.route) {
//                    popUpTo(DestinasiHomeInstruktur.route) { inclusive = true }
//                }
//            })
//        }
//        composable(DestinasiDetailInstruktur.routeWithArg) { backStackEntry ->
//            val id_instruktur =
//                backStackEntry.arguments?.getString(DestinasiDetailInstruktur.ID_Instru)?.toIntOrNull()
//            id_instruktur?.let {
//                DetailInstrukturView(
//                    id_instruktur = it ,
//                    navigateBack = {
//                        navController.navigate(DestinasiHomeInstruktur.route) {
//                            popUpTo(DestinasiHomeSiswa.route) { inclusive = true }
//                        }
//                    },
//                    onEditClick = {
//                        navController.navigate("${DestinasiUpdateSiswa.route}/$it")
//                    }
//                )
//            }
//        }
//        composable(
//            DestinasiUpdateInstruktur.routesWithArg,
//            arguments = listOf(navArgument(DestinasiUpdateInstruktur.ID_Instru) {
//                type = NavType.IntType // Ubah menjadi NavType.IntType untuk ID bertipe Int
//            })
//        ) { backStackEntry ->
//            val id_instruktur = backStackEntry.arguments?.getInt(DestinasiUpdateInstruktur.ID_Instru)
//            id_instruktur?.let {
//                UpdateInstrukturView(
//                    navigateBack = { navController.popBackStack() },
//                    modifier = modifier
//                )
//            }
//        }
//    }

    NavHost(
        navController = navController,
        startDestination = DestinasiHomePendaftaran.route,
        modifier = modifier,
    ) {
        composable(DestinasiHomePendaftaran.route) {
            HomePendftaranScreen(
                navigateTolItemEntry = { navController.navigate(DestinasiEntryPndftrn.route) },
                onDetailClick = { id_pendaftaran ->
                    navController.navigate("${DestinasiDetailPendaftran.route}/$id_pendaftaran") {
                        popUpTo(DestinasiHomePendaftaran.route) { inclusive = true }
                    }
                }
            )
        }
        composable(DestinasiEntryPndftrn.route) {
            EntryPndftrnScreen(navigateBack = {
                navController.navigate(DestinasiHomePendaftaran.route) {
                    popUpTo(DestinasiHomePendaftaran.route) { inclusive = true }
                }
            })
        }
        composable(DestinasiDetailPendaftran.routeWithArg) { backStackEntry ->
            val id_pendaftaran =
                backStackEntry.arguments?.getString(DestinasiDetailPendaftran.ID_Pendaftaran)?.toIntOrNull()
            id_pendaftaran?.let {
                DetailPendaftaranView(
                    id_pendaftaran = it,
                    navigateBack = {
                        navController.navigate(DestinasiHomePendaftaran.route) {
                            popUpTo(DestinasiHomePendaftaran.route) { inclusive = true }
                        }
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdatePendaftaran.route}/$it")
                    }
                )
            }
        }
        composable(
            DestinasiUpdatePendaftaran.routesWithArg,
            arguments = listOf(navArgument(DestinasiUpdatePendaftaran.ID_Pendaftaran) {
                type = NavType.IntType // Ubah menjadi NavType.IntType untuk ID bertipe Int
            })
        ) { backStackEntry ->
            val id_pendaftaran = backStackEntry.arguments?.getInt(DestinasiUpdatePendaftaran.ID_Pendaftaran)
            id_pendaftaran?.let {
                UpdatePendfatranView(
                    navigateBack = { navController.popBackStack() },
                    modifier = modifier
                )
            }
        }
    }


}
