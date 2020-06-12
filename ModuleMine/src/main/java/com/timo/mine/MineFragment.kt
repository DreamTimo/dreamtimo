package com.timo.mine

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.previewlibrary.GPreviewBuilder
import com.previewlibrary.ZoomMediaLoader
import com.previewlibrary.view.BasePhotoFragment
import com.timo.base.BaseTools
import com.timo.base.base.base_fragment.BaseFragment
import com.timo.base.route.RouteConstant
import com.timo.base.route.RouteUtil
import com.timo.mine.bean.ImagePreviewBean
import com.timo.mine.image_preview.ImagePreviewLoader
import kotlinx.android.synthetic.main.fragment_mine.*
import me.kareluo.imaging.IMGEditActivity
import java.io.File
import java.util.*

/**
 * Created by lykj on 2017/9/12.
 */
@Route(path = RouteConstant.fragment_mine)
class MineFragment : BaseFragment(), View.OnClickListener {

    override fun getContentResId(): Int = R.layout.fragment_mine

    private var image_huahuishi: ArrayList<ImagePreviewBean>? = null
    private var image_jinlian: ArrayList<ImagePreviewBean>? = null
    private var httpData: ArrayList<ImagePreviewBean>? = null
    private var projectData: ArrayList<ImagePreviewBean>? = null
    private var recyclerData: ArrayList<ImagePreviewBean>? = null
    private var mPicFile: File? = null
    private var mRequestCode: Int? = 1002

    override fun initEvent(view: View) {
        BaseTools.setTitleBar(view_title_mine, "心得经验")
        val html = "<font color='#ff0000'>点击-></font><font color='#0000FF'>fir应用(N多应用)<font>"
        val charSequence = Html.fromHtml(html)
        tv_project.text = charSequence

        tv_project.setOnClickListener(this)
        tv_mayun_project.setOnClickListener(this)
        tv_http.setOnClickListener(this)
        tv_project_description.setOnClickListener(this)
        tv_choice_image.setOnClickListener(this)
        tv_work_huahuishi.setOnClickListener(this)
        tv_recyclerview.setOnClickListener(this)
        tv_work_jinlian.setOnClickListener(this)
        ZoomMediaLoader.getInstance().init(ImagePreviewLoader())
    }
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_mayun_project -> {
                RouteUtil.to_project_mayun()
            }
            R.id.tv_project -> {
                RouteUtil.to_project_github()
            }
            R.id.tv_work_huahuishi -> {
                if (image_huahuishi == null) {
                    image_huahuishi = ArrayList()
                    image_huahuishi!!.add(ImagePreviewBean("12"))
                    image_huahuishi!!.add(ImagePreviewBean("13"))
                    image_huahuishi!!.add(ImagePreviewBean("11"))
                    image_huahuishi!!.add(ImagePreviewBean("9"))
                    image_huahuishi!!.add(ImagePreviewBean("7"))
                    image_huahuishi!!.add(ImagePreviewBean("5"))
                    image_huahuishi!!.add(ImagePreviewBean("4"))
                    image_huahuishi!!.add(ImagePreviewBean("3"))
                    image_huahuishi!!.add(ImagePreviewBean("10"))
                    image_huahuishi!!.add(ImagePreviewBean("16"))
                    image_huahuishi!!.add(ImagePreviewBean("17"))
                    image_huahuishi!!.add(ImagePreviewBean("18"))
                    image_huahuishi!!.add(ImagePreviewBean("6"))
                    image_huahuishi!!.add(ImagePreviewBean("8"))
                    image_huahuishi!!.add(ImagePreviewBean("14"))
                    image_huahuishi!!.add(ImagePreviewBean("15"))
                    image_huahuishi!!.add(ImagePreviewBean("2"))
                    image_huahuishi!!.add(ImagePreviewBean("1"))
                }
                showPics(image_huahuishi!!)
            }
            R.id.tv_project_description -> {
                if (projectData == null) {
                    projectData = ArrayList()
                    projectData!!.add(ImagePreviewBean("24"))
                    projectData!!.add(ImagePreviewBean("25"))
                    projectData!!.add(ImagePreviewBean("26"))
                    projectData!!.add(ImagePreviewBean("27"))
                    projectData!!.add(ImagePreviewBean("28"))
                    projectData!!.add(ImagePreviewBean("29"))
                    projectData!!.add(ImagePreviewBean("30"))
                }
                showPics(projectData!!)
            }
            R.id.tv_recyclerview -> {
                if (recyclerData == null) {
                    recyclerData = ArrayList()
                    recyclerData!!.add(ImagePreviewBean("31"))
                    recyclerData!!.add(ImagePreviewBean("32"))
                    recyclerData!!.add(ImagePreviewBean("33"))
                    recyclerData!!.add(ImagePreviewBean("34"))
                }
                GPreviewBuilder.from(this)
                        .setData(recyclerData!!)
                        .setUserFragment(BasePhotoFragment::class.java)
                        .setCurrentIndex(0)
                        .setSingleFling(true)
                        .setType(GPreviewBuilder.IndicatorType.Number)
                        .start()
            }
            R.id.tv_choice_image -> {
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(4)// 每行显示个数 int
                        .selectionMode(if (true) PictureConfig.SINGLE else PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                        .enableCrop(false)// 是否裁剪 true or false
                        .compress(true)// 是否压缩 true or false
                        .forResult(PictureConfig.CHOOSE_REQUEST)//结果回调onActivityResult code
            }
            R.id.tv_http -> {
                if (httpData == null) {
                    httpData = ArrayList()
                    httpData!!.add(ImagePreviewBean("19"))
                    httpData!!.add(ImagePreviewBean("20"))
                    httpData!!.add(ImagePreviewBean("21"))
                    httpData!!.add(ImagePreviewBean("22"))
                    httpData!!.add(ImagePreviewBean("23"))
                }
                GPreviewBuilder.from(this)
                        .setData(httpData!!)
                        .setUserFragment(BasePhotoFragment::class.java)
                        .setCurrentIndex(0)
                        .setSingleFling(true)
                        .setType(GPreviewBuilder.IndicatorType.Number)
                        .start()
            }
            R.id.tv_work_jinlian->{
                if (image_jinlian == null) {
                    image_jinlian = ArrayList()
                    image_jinlian!!.add(ImagePreviewBean("41"))
                    image_jinlian!!.add(ImagePreviewBean("42"))
                    image_jinlian!!.add(ImagePreviewBean("43"))
                    image_jinlian!!.add(ImagePreviewBean("44"))
                    image_jinlian!!.add(ImagePreviewBean("46"))
                    image_jinlian!!.add(ImagePreviewBean("47"))
                    image_jinlian!!.add(ImagePreviewBean("45"))
                    image_jinlian!!.add(ImagePreviewBean("48"))
                    image_jinlian!!.add(ImagePreviewBean("49"))
                    image_jinlian!!.add(ImagePreviewBean("40"))
                }
                showPics(image_jinlian!!)
            }
        }
    }

    private fun showPics(data: ArrayList<ImagePreviewBean>) {
        GPreviewBuilder.from(this)
                .setData(data)
                .setUserFragment(BasePhotoFragment::class.java)
                .setCurrentIndex(0)
                .setSingleFling(true)
                .setType(GPreviewBuilder.IndicatorType.Number)
                .start()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PictureConfig.CHOOSE_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    var path = PictureSelector.obtainMultipleResult(data)[0].path
                    mPicFile = File(activity!!.cacheDir, UUID.randomUUID().toString() + ".jpg")
                    startActivityForResult(Intent(activity, IMGEditActivity::class.java)
                            .putExtra(IMGEditActivity.EXTRA_IMAGE_URI, Uri.fromFile(File(path)))
                            .putExtra(IMGEditActivity.EXTRA_IMAGE_SAVE_PATH, mPicFile?.absolutePath), mRequestCode!!)
                }
            }
            mRequestCode -> {
                if (resultCode == Activity.RESULT_OK) {
                    BaseTools.load(activity, File(mPicFile!!.absolutePath), iv_image)
                }
            }
        }
    }
}
