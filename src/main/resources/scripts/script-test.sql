INSERT INTO public.categoria_material(
	cod_catmaterial, flg_anulado, categoria_material, usu_creacion, usu_actualizacion, equipo_creacion, equipo_actualizacion,fec_creacion,fec_actualizacion)
	VALUES ('001', false, 'LECHE PASTEURIZADA', 'usu1', 'usu1', 'eq1', 'eq2', '2022-06-21T00:00:00', '2022-06-21T00:00:00');

INSERT INTO public.categoria_material(
	cod_catmaterial, flg_anulado, categoria_material, usu_creacion, usu_actualizacion, equipo_creacion, equipo_actualizacion,fec_creacion,fec_actualizacion)
	VALUES ('002', false, 'QUESO PASTEURIZADO', 'usu1', 'usu1', 'eq1', 'eq2', '2022-06-21T00:00:00', '2022-06-21T00:00:00');


INSERT INTO public.material(
	id_material, fec_creacion, nombre_largo, presentacion, nombre_corto, fec_actualizacion, flg_anulado, dias_vencimiento, tipo_vencimiento, cod_catmaterial, usu_creacion, usu_actualizacion, equipo_creacion, equipo_actualizacion)
	VALUES (1,'2022-06-21T00:00:00', 'LECHE TARRO', 'LEC TAR', 'LT', '2022-06-21T00:00:00', false, 4, 'TV1', '001', 'usu1', 'usu1', 'eq1', 'eq2');


INSERT INTO public.material(
	id_material, fec_creacion, nombre_largo, presentacion, nombre_corto, fec_actualizacion, flg_anulado, dias_vencimiento, tipo_vencimiento, cod_catmaterial, usu_creacion, usu_actualizacion, equipo_creacion, equipo_actualizacion)
	VALUES (2,'2022-06-21T00:00:00', 'QUESO EMPAQUETADO', 'QUE EMP', 'QE', '2022-06-21T00:00:00', false, 4, 'TV1', '002', 'usu1', 'usu1', 'eq1', 'eq2');

INSERT INTO public.centro(
	id_centro, fec_creacion, equipo_creacion, equipo_actualizacion, fec_actualizacion, usu_creacion, usu_actualizacion, flg_anulado, centro, id_pais, id_departamento, latitud, longitud)
	VALUES ('001', '2022-06-21T00:00:00', 'eq1', 'eq1', '2022-06-21T00:00:00', 'usu1', 'usu1', false, 'CENTRO 1', 'PER', 2, 50, 50);

INSERT INTO public.centro(
	id_centro, fec_creacion, equipo_creacion, equipo_actualizacion, fec_actualizacion, usu_creacion, usu_actualizacion, flg_anulado, centro, id_pais, id_departamento, latitud, longitud)
	VALUES ('002', '2022-06-21T00:00:00', 'eq1', 'eq1', '2022-06-21T00:00:00', 'usu1', 'usu1', false, 'CENTRO 2', 'PER', 2, 50, 50);


INSERT INTO public.centro_material(
	id_centro, id_material, fec_creacion, equipo_creacion, equipo_actualizacion, fec_actualizacion, usu_creacion, usu_actualizacion)
	VALUES ('001', 1, '2022-06-21T00:00:00', 'eq1', 'eq1', '2022-06-21T00:00:00', 'usu1', 'usu2');


INSERT INTO public.centro_material(
	id_centro, id_material, fec_creacion, equipo_creacion, equipo_actualizacion, fec_actualizacion, usu_creacion, usu_actualizacion)
	VALUES ('002', 2, '2022-06-21T00:00:00', 'eq1', 'eq1', '2022-06-21T00:00:00', 'usu1', 'usu1');	

INSERT INTO public.linea(
	id_linea, equipo_actualizacion, equipo_creacion, fec_actualizacion, fec_creacion, usu_actualizacion, usu_creacion, codificacion, descripcion, flg_anulado, flq_reproceso, id_centro, id_seccion_produccion, limite_inferior, limite_superior, linea, pst_trbjo, time_out_impresion)
	VALUES (1, 'eq1', 'eq1', '2022-06-21T00:00:00', '2022-06-21T00:00:00', 'usu1', 'usu1', 1, 'descripcion', false, false, '002', 'SP', 10.2, 10.3, 'Linea 1', 'pst trabajo', 30);
	
INSERT INTO public.unidad_medida(
	id_unidad_medida, equipo_actualizacion, equipo_creacion, fec_actualizacion, fec_creacion, usu_actualizacion, usu_creacion, unidad_medida, unidad_medida_larga)
	VALUES ('U1', 'eq1', 'eq1', '2022-06-21T00:00:00', '2022-06-21T00:00:00', 'usu1', 'usu1', 'kg', 'kilogramo');

INSERT INTO public.unidad_alternativo(
	id_material, id_unidad_medida, contador, denominador, flg_anulado)
	VALUES (1, 'U1', 1, 1, false);

	/*PRUEBA 28112022 HOY DIA 28112022*/