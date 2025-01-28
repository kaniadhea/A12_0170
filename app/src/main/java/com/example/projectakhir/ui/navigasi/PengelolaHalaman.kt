package com.example.projectakhir.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectakhir.ui.DestinasiHomePertama
import com.example.projectakhir.ui.HomeAwalView
import com.example.projectakhir.ui.View.Siswa.*
import com.example.projectakhir.ui.View.Instruktur.*
import com.example.projectakhir.ui.View.Kursus.*
import com.example.projectakhir.ui.View.Pendaftaran.*

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomePertama.route,
        modifier = Modifier
    ) {
        // Halaman Home Awal
        composable(
            route = DestinasiHomePertama.route
        ) {
            HomeAwalView(
                onSwa = { navController.navigate(DestinasiHomeSiswa.route) },
                onIns = { navController.navigate(DestinasiHomeInstruktur.route) },
                onPen = { navController.navigate(DestinasiHomePendaftaran.route) },
                onKur = { navController.navigate(DestinasiHomeKursus.route) }
            )
        }

        // Halaman Home Siswa
        composable(DestinasiHomeSiswa.route) {
            HomeScreenSiswa(
                navigateTolItemEntry = { navController.navigate(DestinasiEntrySiswa.route) },
                onDetailClick = { id_siswa ->
                    // Navigasi ke halaman detail dengan ID siswa
                    navController.navigate("${DestinasiDetailSiswa.route}/$id_siswa")
                }
            )
        }

        composable(DestinasiEntrySiswa.route) {
            EntrySwaScreen(navigateBack = {
                navController.navigate(DestinasiHomeSiswa.route) {
                    popUpTo(DestinasiHomeSiswa.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            route = DestinasiDetailSiswa.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailSiswa.ID_Siswa) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            // Ambil argumen id_siswa dari NavBackStackEntry
            val id_siswa = backStackEntry.arguments?.getInt(DestinasiDetailSiswa.ID_Siswa)

            id_siswa?.let {
                DetailSiswaView(
                    id_siswa = it,
                    navigateBack = {
                        navController.navigate(DestinasiHomeSiswa.route) {
                            popUpTo(DestinasiHomeSiswa.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick = { siswaId ->
                        navController.navigate("${DestinasiUpdateSiswa.route}/$siswaId")
                    }
                )
            }
        }

        composable(
            route = DestinasiUpdateSiswa.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateSiswa.ID_Siswa) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id_siswa = backStackEntry.arguments?.getInt(DestinasiUpdateSiswa.ID_Siswa)

            id_siswa?.let {
                UpdateSiswaView(
                    onBack = {
                        navController.navigate(DestinasiHomeSiswa.route)
                    },
                    navigateBack = {
                        navController.popBackStack()
                    },
                )
            }
        }





        //INSTRUKTUR
        composable(DestinasiHomeInstruktur.route) {
            HomeScreenInstruktur(
                navigateTolItemEntry = { navController.navigate(DestinasiEntryInstruktur.route) },
                onDetailClick = { id_instruktur ->
                    // Navigasi ke halaman detail dengan ID siswa
                    navController.navigate("${DestinasiDetailInstruktur.route}/$id_instruktur")
                }
            )
        }

        // Halaman Entry Siswa
        composable(DestinasiEntryInstruktur.route) {
            EntryInstruScreen(navigateBack = {
                navController.navigate(DestinasiHomeInstruktur.route) {
                    popUpTo(DestinasiHomeInstruktur.route) {
                        inclusive = true
                    }
                }
            })
        }

        // Halaman Detail Siswa
        composable(
            route = DestinasiDetailInstruktur.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailInstruktur.ID_Instru) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            // Ambil argumen id_siswa dari NavBackStackEntry
            val id_instruktur = backStackEntry.arguments?.getInt(DestinasiDetailInstruktur.ID_Instru)

            id_instruktur?.let {
                DetailInstrukturView (
                    id_instruktur = it,
                    navigateBack = {
                        navController.navigate(DestinasiHomeInstruktur.route) {
                            popUpTo(DestinasiHomeInstruktur.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick = { instrukturID ->
                        navController.navigate("${DestinasiUpdateInstruktur.route}/$instrukturID")
                    }
                )
            }
        }

        // Halaman Update Siswa
        composable(
            route = DestinasiUpdateInstruktur.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateInstruktur.ID_Instru) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id_instruktur = backStackEntry.arguments?.getInt(DestinasiUpdateInstruktur.ID_Instru)

            id_instruktur?.let {
                UpdateInstrukturView(
                    onBack = {
                        navController.navigate(DestinasiHomeInstruktur.route)
                    },
                    navigateBack = {
                        navController.popBackStack()
                    },
                )
            }
        }





//Kursus
        composable(DestinasiHomeKursus.route) {
            HomeKursusScreen(
                navigateTolItemEntry = { navController.navigate(DestinasiEntryKursus.route) },
                onDetailClick = { id_kursus ->
                    // Navigasi ke halaman detail dengan ID siswa
                    navController.navigate("${DestinasiDetailKursus.route}/$id_kursus")
                }
            )
        }

        // Halaman Entry Siswa
        composable(DestinasiEntryKursus.route) {
            EntryKurScreen(navigateBack = {
                navController.navigate(DestinasiHomeKursus.route) {
                    popUpTo(DestinasiHomeKursus.route) {
                        inclusive = true
                    }
                }
            })
        }

        // Halaman Detail Siswa
        composable(
            route = DestinasiDetailKursus.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailKursus.ID_Kursus) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            // Ambil argumen id_siswa dari NavBackStackEntry
            val id_kursus = backStackEntry.arguments?.getInt(DestinasiDetailKursus.ID_Kursus)

            id_kursus?.let {
                DetailKursusView (
                    id_kursus = it,
                    navigateBack = {
                        navController.navigate(DestinasiHomeKursus.route) {
                            popUpTo(DestinasiHomeKursus.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick = { kursusID ->
                        navController.navigate("${DestinasiUpdateInstruktur.route}/$kursusID")
                    }
                )
            }
        }

        // Halaman Update Siswa
        composable(
            route = DestinasiUpdateKursus.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateKursus.ID_Kursus) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id_kursus = backStackEntry.arguments?.getInt(DestinasiUpdateKursus.ID_Kursus)

            id_kursus?.let {
                UpdateKursusView(
                    onBack = {
                        navController.navigate(DestinasiHomeKursus.route)
                    },
                    navigateBack = {
                        navController.popBackStack()
                    },
                )
            }
        }
        composable(DestinasiHomeKursus.route) {
            HomeKursusScreen(
                navigateTolItemEntry = { navController.navigate(DestinasiEntryKursus.route) },
                onDetailClick = { id_kursus ->
                    // Navigasi ke halaman detail dengan ID siswa
                    navController.navigate("${DestinasiDetailKursus.route}/$id_kursus")
                }
            )
        }

        // Halaman Entry Siswa
        composable(DestinasiEntryKursus.route) {
            EntryKurScreen(navigateBack = {
                navController.navigate(DestinasiHomeKursus.route) {
                    popUpTo(DestinasiHomeKursus.route) {
                        inclusive = true
                    }
                }
            })
        }

        // Halaman Detail Siswa
        composable(
            route = DestinasiDetailKursus.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailKursus.ID_Kursus) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            // Ambil argumen id_siswa dari NavBackStackEntry
            val id_kursus = backStackEntry.arguments?.getInt(DestinasiDetailKursus.ID_Kursus)

            id_kursus?.let {
                DetailKursusView (
                    id_kursus = it,
                    navigateBack = {
                        navController.navigate(DestinasiHomeKursus.route) {
                            popUpTo(DestinasiHomeKursus.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick = { kursusID ->
                        navController.navigate("${DestinasiUpdateKursus.route}/$kursusID")
                    }
                )
            }
        }

        // Halaman Update Siswa
        composable(
            route = DestinasiUpdateKursus.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateKursus.ID_Kursus) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id_kursus = backStackEntry.arguments?.getInt(DestinasiUpdateKursus.ID_Kursus)

            id_kursus?.let {
                UpdateKursusView(
                    onBack = {
                        navController.navigate(DestinasiHomeInstruktur.route)
                    },
                    navigateBack = {
                        navController.popBackStack()
                    },
                )
            }
        }




//PENDAFTARAN
        composable(DestinasiHomePendaftaran.route) {
            HomePendftaranScreen(
                navigateTolItemEntry = { navController.navigate(DestinasiEntryPndftrn.route) },
                onDetailClick = { id_pendaftaran ->
                    // Navigasi ke halaman detail dengan ID siswa
                    navController.navigate("${DestinasiDetailPendaftran.route}/$id_pendaftaran")
                }
            )
        }

        composable(DestinasiEntryPndftrn.route) {
            EntryPndftrnScreen(navigateBack = {
                navController.navigate(DestinasiHomePendaftaran.route) {
                    popUpTo(DestinasiHomePendaftaran.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            route = DestinasiDetailPendaftran.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailPendaftran.ID_Pendaftaran) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            // Ambil argumen id_siswa dari NavBackStackEntry
            val id_pendaftaran = backStackEntry.arguments?.getInt(DestinasiDetailPendaftran.ID_Pendaftaran)

            id_pendaftaran?.let {
                DetailPendaftaranView (
                    id_pendaftaran = it,
                    navigateBack = {
                        navController.navigate(DestinasiHomePendaftaran.route) {
                            popUpTo(DestinasiHomePendaftaran.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick = { pendaftaranID ->
                        navController.navigate("${DestinasiUpdatePendaftaran.route}/$pendaftaranID")
                    }
                )
            }
        }

        // Halaman Update Siswa
        composable(
            route = DestinasiUpdatePendaftaran.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdatePendaftaran.ID_Pendaftaran) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id_pendaftaran = backStackEntry.arguments?.getInt(DestinasiUpdatePendaftaran.ID_Pendaftaran)

            id_pendaftaran?.let {
                UpdatePendfatranView(
                    id_pendaftaran = id,
                    onBack = {
                        navController.navigate(DestinasiHomePendaftaran.route)
                    },
                    navigateBack = {
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}
