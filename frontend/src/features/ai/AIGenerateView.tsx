import { useState } from "react";
import { motion } from "motion/react";
import { Sparkles, ArrowLeft } from "lucide-react";
import { apiClient } from "@/shared/api/client";

interface AIGenerateViewProps {
  onNavigate: (page: string) => void;
}

interface AiDesignResponse {
  title: string;
  summary: string;
  sections: string[];
  components: string[];
  palette: string[];
  nextSteps: string[];
}

export function AIGenerateView({ onNavigate }: AIGenerateViewProps) {
  const [prompt, setPrompt] = useState("");
  const [generating, setGenerating] = useState(false);
  const [result, setResult] = useState<AiDesignResponse | null>(null);
  const [error, setError] = useState<string | null>(null);

  const handleGenerate = async () => {
    if (!prompt.trim()) return;
    setGenerating(true);
    setError(null);

    try {
      const data = await apiClient.post<AiDesignResponse>("/api/ai/design", {
        body: {
          prompt: prompt.trim(),
          audience: "디자인/개발 전공자",
          tone: "실용적이고 선명한",
        },
      });
      setResult(data);
    } catch {
      setError("AI 디자인 생성에 실패했습니다. 백엔드 서버가 켜져 있는지 확인해주세요.");
    } finally {
      setGenerating(false);
    }
  };

  return (
    <div className="min-h-screen" style={{ backgroundColor: "#FFFBF7" }}>
      <div className="max-w-4xl mx-auto px-6 py-12">
        <motion.button
          initial={{ opacity: 0, x: -20 }}
          animate={{ opacity: 1, x: 0 }}
          whileHover={{ scale: 1.05 }}
          onClick={() => onNavigate('home')}
          className="flex items-center gap-2 mb-8 text-gray-600"
        >
          <ArrowLeft size={20} />
          홈으로 돌아가기
        </motion.button>

        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="text-center mb-12"
        >
          <Sparkles size={64} className="mx-auto mb-4" style={{ color: "#1CB0F6" }} />
          <h1 className="text-4xl mb-4">AI 디자인 생성</h1>
          <p className="text-xl text-gray-600">AI가 당신의 아이디어를 디자인으로 만들어드립니다</p>
        </motion.div>

        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.2 }}
          className="bg-white rounded-2xl shadow-lg p-8"
        >
          <div className="mb-6">
            <label className="block text-gray-700 mb-3 text-lg">원하는 디자인을 설명해주세요</label>
            <textarea
              value={prompt}
              onChange={(e) => setPrompt(e.target.value)}
              rows={6}
              className="w-full p-4 border-2 border-gray-200 rounded-xl focus:border-[#1CB0F6] focus:outline-none resize-none"
              placeholder="예: 모던하고 미니멀한 스타일의 로그인 페이지를 만들어주세요."
            />
          </div>

          <motion.button
            whileHover={{ scale: 1.02 }}
            whileTap={{ scale: 0.98 }}
            onClick={handleGenerate}
            disabled={!prompt.trim() || generating}
            className="w-full py-4 rounded-xl text-white text-lg disabled:opacity-50"
            style={{ backgroundColor: "#1CB0F6" }}
          >
            {generating ? "생성 중..." : "AI로 디자인 생성하기"}
          </motion.button>

          {error && (
            <p className="mt-4 text-sm text-red-500">{error}</p>
          )}
        </motion.div>

        {result && (
          <motion.div
            initial={{ opacity: 0, y: 16 }}
            animate={{ opacity: 1, y: 0 }}
            className="mt-8 bg-white rounded-2xl shadow-lg p-8 border border-blue-100"
          >
            <div className="mb-6">
              <p className="text-sm font-bold text-[#1CB0F6] mb-2">Generated Design Brief</p>
              <h2 className="text-2xl font-bold text-gray-900 mb-3">{result.title}</h2>
              <p className="text-gray-600 leading-relaxed">{result.summary}</p>
            </div>

            <div className="grid md:grid-cols-2 gap-6">
              <section>
                <h3 className="font-bold text-gray-900 mb-3">화면 구성</h3>
                <div className="space-y-2">
                  {result.sections.map((section) => (
                    <p key={section} className="rounded-xl bg-blue-50 px-4 py-3 text-sm text-gray-700">
                      {section}
                    </p>
                  ))}
                </div>
              </section>

              <section>
                <h3 className="font-bold text-gray-900 mb-3">추천 컴포넌트</h3>
                <div className="flex flex-wrap gap-2">
                  {result.components.map((component) => (
                    <span key={component} className="rounded-full bg-gray-100 px-3 py-2 text-sm font-medium text-gray-700">
                      {component}
                    </span>
                  ))}
                </div>
              </section>
            </div>

            <div className="mt-6">
              <h3 className="font-bold text-gray-900 mb-3">컬러 팔레트</h3>
              <div className="flex flex-wrap gap-3">
                {result.palette.map((color) => (
                  <div key={color} className="flex items-center gap-2 rounded-xl border border-gray-100 px-3 py-2">
                    <span className="h-6 w-6 rounded-full border border-black/10" style={{ backgroundColor: color }} />
                    <span className="text-sm font-medium text-gray-700">{color}</span>
                  </div>
                ))}
              </div>
            </div>

            <div className="mt-6">
              <h3 className="font-bold text-gray-900 mb-3">다음 작업</h3>
              <ol className="space-y-2">
                {result.nextSteps.map((step, index) => (
                  <li key={step} className="flex gap-3 text-sm text-gray-700">
                    <span className="font-bold text-[#1CB0F6]">{index + 1}</span>
                    {step}
                  </li>
                ))}
              </ol>
            </div>
          </motion.div>
        )}
      </div>
    </div>
  );
}
