package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.zip;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Simple product types.
 */
public abstract class Tuple implements Serializable {
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple0 tuple) {
        return emptyList();
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple1<T> tuple) {
        return Arrays.asList(tuple._1);
    }

    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple2<? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple3<? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple4<? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple5<? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple6<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple7<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple8<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple9<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple10<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple11<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple12<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple13<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple14<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple15<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple16<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple17<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple18<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple19<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple20<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple21<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple22<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple23<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple24<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple25<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple26<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple27<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple28<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple29<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple30<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple31<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple32<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31, tuple._32);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple33<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31, tuple._32, tuple._33);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple34<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31, tuple._32, tuple._33, tuple._34);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple35<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31, tuple._32, tuple._33, tuple._34, tuple._35);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple36<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31, tuple._32, tuple._33, tuple._34, tuple._35, tuple._36);
    }

    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple37<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31, tuple._32, tuple._33, tuple._34, tuple._35, tuple._36, tuple._37);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple38<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31, tuple._32, tuple._33, tuple._34, tuple._35, tuple._36, tuple._37, tuple._38);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple39<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31, tuple._32, tuple._33, tuple._34, tuple._35, tuple._36, tuple._37, tuple._38, tuple._39);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple40<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31, tuple._32, tuple._33, tuple._34, tuple._35, tuple._36, tuple._37, tuple._38, tuple._39, tuple._40);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple41<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31, tuple._32, tuple._33, tuple._34, tuple._35, tuple._36, tuple._37, tuple._38, tuple._39, tuple._40, tuple._41);
    }
    
    /**
     * @return {@code tuple} as a list of common super type.
     */
    public static <T> List<T> asList(Tuple42<? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T, ? extends T> tuple) {
        return Arrays.asList(tuple._1, tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7, tuple._8, tuple._9, tuple._10, tuple._11, tuple._12, tuple._13, tuple._14, tuple._15, tuple._16, tuple._17, tuple._18, tuple._19, tuple._20, tuple._21, tuple._22, tuple._23, tuple._24, tuple._25, tuple._26, tuple._27, tuple._28, tuple._29, tuple._30, tuple._31, tuple._32, tuple._33, tuple._34, tuple._35, tuple._36, tuple._37, tuple._38, tuple._39, tuple._40, tuple._41, tuple._42);
    }
    
    /**
     * <i>Unsafe!</i> Will fail if given more than 32 arguments.
     * 
     * @return tuple of given values {@code ts}.
     */
    public static Tuple of(Object... ts) {
        switch (ts.length) {
            case 0: return Tuple.of();
            case 1: return Tuple.of(ts[0]);
            case 2: return Tuple.of(ts[0], ts[1]);
            case 3: return Tuple.of(ts[0], ts[1], ts[2]);
            case 4: return Tuple.of(ts[0], ts[1], ts[2], ts[3]);
            case 5: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4]);
            case 6: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5]);
            case 7: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6]);
            case 8: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7]);
            case 9: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8]);
            case 10: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9]);
            case 11: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10]);
            case 12: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11]);
            case 13: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12]);
            case 14: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13]);
            case 15: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14]);
            case 16: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15]);
            case 17: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16]);
            case 18: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17]);
            case 19: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18]);
            case 20: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19]);
            case 21: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20]);
            case 22: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21]);
            case 23: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22]);
            case 24: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23]);
            case 25: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24]);
            case 26: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25]);
            case 27: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26]);
            case 28: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27]);
            case 29: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28]);
            case 30: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29]);
            case 31: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30]);
            case 32: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30], ts[31]);
            case 33: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30], ts[31], ts[32]);
            case 34: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30], ts[31], ts[32], ts[33]);
            case 35: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30], ts[31], ts[32], ts[33], ts[34]);
            case 36: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30], ts[31], ts[32], ts[33], ts[34], ts[35]);
            case 37: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30], ts[31], ts[32], ts[33], ts[34], ts[35], ts[36]);
            case 38: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30], ts[31], ts[32], ts[33], ts[34], ts[35], ts[36], ts[37]);
            case 39: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30], ts[31], ts[32], ts[33], ts[34], ts[35], ts[36], ts[37], ts[38]);
            case 40: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30], ts[31], ts[32], ts[33], ts[34], ts[35], ts[36], ts[37], ts[38], ts[39]);
            case 41: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30], ts[31], ts[32], ts[33], ts[34], ts[35], ts[36], ts[37], ts[38], ts[39], ts[40]);
            case 42: return Tuple.of(ts[0], ts[1], ts[2], ts[3], ts[4], ts[5], ts[6], ts[7], ts[8], ts[9], ts[10], ts[11], ts[12], ts[13], ts[14], ts[15], ts[16], ts[17], ts[18], ts[19], ts[20], ts[21], ts[22], ts[23], ts[24], ts[25], ts[26], ts[27], ts[28], ts[29], ts[30], ts[31], ts[32], ts[33], ts[34], ts[35], ts[36], ts[37], ts[38], ts[39], ts[40], ts[41]);
        }
        throw new UnsupportedOperationException("Tuple43 not implemented");
    }
    
    /**
     * @return an empty tuple.
     */
    public static Tuple0 of() {
        return new Tuple0();
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1> Tuple1<T1> of(T1 _1) {
        return new Tuple1<T1>(_1);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2> Tuple2<T1, T2> of(T1 _1, T2 _2) {
        return Pair.of(_1, _2);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 _1, T2 _2, T3 _3) {
        return new Tuple3<T1, T2, T3>(_1, _2, _3);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 _1, T2 _2, T3 _3, T4 _4) {
        return new Tuple4<T1, T2, T3, T4>(_1, _2, _3, _4);
    }

    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5) {
        return new Tuple5<T1, T2, T3, T4, T5>(_1, _2, _3, _4, _5);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6) {
        return new Tuple6<T1, T2, T3, T4, T5, T6>(_1, _2, _3, _4, _5, _6);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7) {
        return new Tuple7<T1, T2, T3, T4, T5, T6, T7>(_1, _2, _3, _4, _5, _6, _7);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8) {
        return new Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>(_1, _2, _3, _4, _5, _6, _7, _8);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9) {
        return new Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>(_1, _2, _3, _4, _5, _6, _7, _8, _9);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10) {
        return new Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11) {
        return new Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12) {
        return new Tuple12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13) {
        return new Tuple13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14) {
        return new Tuple14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15) {
        return new Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16) {
        return new Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> Tuple17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17) {
        return new Tuple17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18) {
        return new Tuple18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> Tuple19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19) {
        return new Tuple19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> Tuple20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20) {
        return new Tuple20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21) {
        return new Tuple21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22) {
        return new Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23> Tuple23<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23) {
        return new Tuple23<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24> Tuple24<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24) {
        return new Tuple24<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25> Tuple25<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25) {
        return new Tuple25<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26> Tuple26<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26) {
        return new Tuple26<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27> Tuple27<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27) {
        return new Tuple27<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28> Tuple28<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28) {
        return new Tuple28<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29> Tuple29<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29) {
        return new Tuple29<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30> Tuple30<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30) {
        return new Tuple30<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31> Tuple31<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31) {
        return new Tuple31<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32> Tuple32<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32) {
        return new Tuple32<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33> Tuple33<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32, T33 _33) {
        return new Tuple33<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34> Tuple34<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32, T33 _33, T34 _34) {
        return new Tuple34<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35> Tuple35<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32, T33 _33, T34 _34, T35 _35) {
        return new Tuple35<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36> Tuple36<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32, T33 _33, T34 _34, T35 _35, T36 _36) {
        return new Tuple36<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36);
    }

    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37> Tuple37<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32, T33 _33, T34 _34, T35 _35, T36 _36, T37 _37) {
        return new Tuple37<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38> Tuple38<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32, T33 _33, T34 _34, T35 _35, T36 _36, T37 _37, T38 _38) {
        return new Tuple38<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39> Tuple39<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32, T33 _33, T34 _34, T35 _35, T36 _36, T37 _37, T38 _38, T39 _39) {
        return new Tuple39<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40> Tuple40<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32, T33 _33, T34 _34, T35 _35, T36 _36, T37 _37, T38 _38, T39 _39, T40 _40) {
        return new Tuple40<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41> Tuple41<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32, T33 _33, T34 _34, T35 _35, T36 _36, T37 _37, T38 _38, T39 _39, T40 _40, T41 _41) {
        return new Tuple41<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41);
    }
    
    /**
     * @return tuple of given arguments.
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42> Tuple42<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42> of(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10, T11 _11, T12 _12, T13 _13, T14 _14, T15 _15, T16 _16, T17 _17, T18 _18, T19 _19, T20 _20, T21 _21, T22 _22, T23 _23, T24 _24, T25 _25, T26 _26, T27 _27, T28 _28, T29 _29, T30 _30, T31 _31, T32 _32, T33 _33, T34 _34, T35 _35, T36 _36, T37 _37, T38 _38, T39 _39, T40 _40, T41 _41, T42 _42) {
        return new Tuple42<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25, _26, _27, _28, _29, _30, _31, _32, _33, _34, _35, _36, _37, _38, _39, _40, _41, _42);
    }
    
    public static interface _1<T> {
        /**
         * @return value in position 1. Normally you should use field {@code _1} instead.
         */
        T get_1();
    }
    
    public static interface _2<T> {
        /**
         * @return value in position 2. Normally you should use field {@code _2} instead.
         */
        T get_2();
    }
    
    public static interface _3<T> {
        /**
         * @return value in position 3. Normally you should use field {@code _3} instead.
         */
        T get_3();
    }
    
    public static interface _4<T> {
        /**
         * @return value in position 4. Normally you should use field {@code _4} instead.
         */
        T get_4();
    }
    
    public static interface _5<T> {
        /**
         * @return value in position 5. Normally you should use field {@code _5} instead.
         */
        T get_5();
    }
    
    public static interface _6<T> {
        /**
         * @return value in position 6. Normally you should use field {@code _6} instead.
         */
        T get_6();
    }
    
    public static interface _7<T> {
        /**
         * @return value in position 7. Normally you should use field {@code _7} instead.
         */
        T get_7();
    }
    
    public static interface _8<T> {
        /**
         * @return value in position 8. Normally you should use field {@code _8} instead.
         */
        T get_8();
    }
    
    public static interface _9<T> {
        /**
         * @return value in position 9. Normally you should use field {@code _9} instead.
         */
        T get_9();
    }
    
    public static interface _10<T> {
        /**
         * @return value in position 10. Normally you should use field {@code _10} instead.
         */
        T get_10();
    }
    
    public static interface _11<T> {
        /**
         * @return value in position 11. Normally you should use field {@code _11} instead.
         */
        T get_11();
    }
    
    public static interface _12<T> {
        /**
         * @return value in position 12. Normally you should use field {@code _12} instead.
         */
        T get_12();
    }
    
    public static interface _13<T> {
        /**
         * @return value in position 13. Normally you should use field {@code _13} instead.
         */
        T get_13();
    }
    
    public static interface _14<T> {
        /**
         * @return value in position 14. Normally you should use field {@code _14} instead.
         */
        T get_14();
    }
    
    public static interface _15<T> {
        /**
         * @return value in position 15. Normally you should use field {@code _15} instead.
         */
        T get_15();
    }
    
    public static interface _16<T> {
        /**
         * @return value in position 16. Normally you should use field {@code _16} instead.
         */
        T get_16();
    }
    
    public static interface _17<T> {
        /**
         * @return value in position 17. Normally you should use field {@code _17} instead.
         */
        T get_17();
    }
    
    public static interface _18<T> {
        /**
         * @return value in position 18. Normally you should use field {@code _18} instead.
         */
        T get_18();
    }
    
    public static interface _19<T> {
        /**
         * @return value in position 19. Normally you should use field {@code _19} instead.
         */
        T get_19();
    }
    
    public static interface _20<T> {
        /**
         * @return value in position 20. Normally you should use field {@code _20} instead.
         */
        T get_20();
    }
    
    public static interface _21<T> {
        /**
         * @return value in position 21. Normally you should use field {@code _21} instead.
         */
        T get_21();
    }
    
    public static interface _22<T> {
        /**
         * @return value in position 22. Normally you should use field {@code _22} instead.
         */
        T get_22();
    }
    
    public static interface _23<T> {
        /**
         * @return value in position 23. Normally you should use field {@code _23} instead.
         */
        T get_23();
    }
    
    public static interface _24<T> {
        /**
         * @return value in position 24. Normally you should use field {@code _24} instead.
         */
        T get_24();
    }
    
    public static interface _25<T> {
        /**
         * @return value in position 25. Normally you should use field {@code _25} instead.
         */
        T get_25();
    }
    
    public static interface _26<T> {
        /**
         * @return value in position 26. Normally you should use field {@code _26} instead.
         */
        T get_26();
    }
    
    public static interface _27<T> {
        /**
         * @return value in position 27. Normally you should use field {@code _27} instead.
         */
        T get_27();
    }
    
    public static interface _28<T> {
        /**
         * @return value in position 28. Normally you should use field {@code _28} instead.
         */
        T get_28();
    }
    
    public static interface _29<T> {
        /**
         * @return value in position 29. Normally you should use field {@code _29} instead.
         */
        T get_29();
    }
    
    public static interface _30<T> {
        /**
         * @return value in position 30. Normally you should use field {@code _30} instead.
         */
        T get_30();
    }
    
    public static interface _31<T> {
        /**
         * @return value in position 31. Normally you should use field {@code _31} instead.
         */
        T get_31();
    }
    
    public static interface _32<T> {
        /**
         * @return value in position 32. Normally you should use field {@code _32} instead.
         */
        T get_32();
    }
    
    public static interface _33<T> {
        /**
         * @return value in position 33. Normally you should use field {@code _33} instead.
         */
        T get_33();
    }
    
    public static interface _34<T> {
        /**
         * @return value in position 34. Normally you should use field {@code _34} instead.
         */
        T get_34();
    }
    
    public static interface _35<T> {
        /**
         * @return value in position 35. Normally you should use field {@code _35} instead.
         */
        T get_35();
    }
    
    public static interface _36<T> {
        /**
         * @return value in position 36. Normally you should use field {@code _36} instead.
         */
        T get_36();
    }

    public static interface _37<T> {
        /**
         * @return value in position 37. Normally you should use field {@code _37} instead.
         */
        T get_37();
    }
    
    public static interface _38<T> {
        /**
         * @return value in position 38. Normally you should use field {@code _38} instead.
         */
        T get_38();
    }
    
    public static interface _39<T> {
        /**
         * @return value in position 39. Normally you should use field {@code _39} instead.
         */
        T get_39();
    }
    
    public static interface _40<T> {
        /**
         * @return value in position 40. Normally you should use field {@code _40} instead.
         */
        T get_40();
    }
    
    public static interface _41<T> {
        /**
         * @return value in position 40. Normally you should use field {@code _41} instead.
         */
        T get_41();
    }
    
    public static interface _42<T> {
        /**
         * @return value in position 40. Normally you should use field {@code _42} instead.
         */
        T get_42();
    }
    
    public static interface Tailable<T> {
        T drop1();
    }

    /**
     * @return untyped array containing values of this tuple.
     */
    public abstract Object[] toArray();
    
    @Override
    public String toString() {
        return "(" + mkString(",", map(Transformers.toString, toArray())) + ")";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (Object o: toArray()) {
            result = prime * result + ((o == null) ? 0 : o.hashCode());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        for (Map.Entry<Object,Object> o: zip(toArray(), ((Tuple)obj).toArray())) {
            if (o.getKey() == null) {
                if (o.getValue() != null)
                    return false;
            } else if (!o.getKey().equals(o.getValue()))
                return false;
        }
        return true;
    }
}
